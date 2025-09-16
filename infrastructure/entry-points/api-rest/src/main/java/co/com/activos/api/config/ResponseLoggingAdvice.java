package co.com.activos.api.config;

import co.com.activos.api.model.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ResponseLoggingAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseLoggingAdvice.class);
    private final ObjectMapper objectMapper;
    private final String responseLogLevel;

    @Autowired
    public ResponseLoggingAdvice(ObjectMapper objectMapper,
                                 @Value("${api.response.log-level:DEBUG}") String responseLogLevel) {
        this.objectMapper = objectMapper;
        this.responseLogLevel = responseLogLevel == null ? "DEBUG" : responseLogLevel.trim().toUpperCase();
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Log any ApiResponse<T> bodies
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse<?> api) {
            String traceId = Optional.ofNullable(api.getTraceId())
                    .orElseGet(() -> Optional.ofNullable(MDC.get("traceId")).orElse(""));
            try {
                String json = objectMapper.writeValueAsString(api);
                if ("INFO".equals(responseLogLevel)) {
                    log.info("response traceId={} body={}", traceId, json);
                } else {
                    log.debug("response traceId={} body={}", traceId, json);
                }
            } catch (JsonProcessingException e) {
                log.warn("Failed to serialize ApiResponse for logging: {}", e.getMessage());
            }
        }
        return body;
    }
}
