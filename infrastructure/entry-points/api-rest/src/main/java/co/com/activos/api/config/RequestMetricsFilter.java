package co.com.activos.api.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.time.Duration;

@Component
@Order(Ordered.LOWEST_PRECEDENCE - 10)
public class RequestMetricsFilter extends OncePerRequestFilter {

    private final MeterRegistry registry;

    @Autowired
    public RequestMetricsFilter(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startNanos = System.nanoTime();
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (request.isAsyncStarted()) {
                // Defer logging/metrics until async completes to capture final status
                request.getAsyncContext().addListener(new AsyncListener() {
                    final long started = startNanos;

                    @Override
                    public void onComplete(AsyncEvent event) {
                        HttpServletRequest req = (HttpServletRequest) event.getSuppliedRequest();
                        HttpServletResponse res = (HttpServletResponse) event.getSuppliedResponse();
                        long end = System.nanoTime();
                        recordAndLog(req, res, started, end);
                    }

                    @Override
                    public void onTimeout(AsyncEvent event) { /* no-op */ }

                    @Override
                    public void onError(AsyncEvent event) { /* no-op */ }

                    @Override
                    public void onStartAsync(AsyncEvent event) { /* no-op */ }
                });
            } else {
                long endNanos = System.nanoTime();
                recordAndLog(request, response, startNanos, endNanos);
            }
        }
    }

    private void recordAndLog(HttpServletRequest request, HttpServletResponse response, long startNanos, long endNanos) {
        double seconds = (endNanos - startNanos) / 1_000_000_000.0;

        String method = request.getMethod();
        String uriPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (uriPattern == null || uriPattern.isBlank()) {
            uriPattern = request.getRequestURI();
        }
        String status = String.valueOf(response.getStatus());

        Timer.builder("http.request.duration")
                .description("HTTP request duration")
                .tag("method", method)
                .tag("uri", uriPattern)
                .tag("status", status)
                .register(registry)
                .record(Duration.ofNanos(endNanos - startNanos));

        registry.counter("http.request.count",
                "method", method,
                "uri", uriPattern,
                "status", status).increment();

        String traceId = MDC.get("traceId");
        logger.info(String.format("traceId=%s method=%s uri=%s status=%s durationSec=%s",
                traceId, method, uriPattern, status, String.format("%.4f", seconds)));
    }
}
