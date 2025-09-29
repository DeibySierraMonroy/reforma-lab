package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.parametrizacionCausales.causalIngreso.CausalIngreso;
import co.com.activos.usecase.CausalIngresoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/causales-ingreso", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CausalIngresoApi {

    private final CausalIngresoUseCase useCase;

    @GetMapping
    public Mono<ApiResponse<List<CausalIngreso>>> findAll(HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("findAll start traceId={} uri={}", traceId, request.getRequestURI());

        return useCase.findByAllForStatus()
                .collectList()
                .map(list ->
                        ApiResponse.success(list, request.getRequestURI(), traceId));
    }
}
