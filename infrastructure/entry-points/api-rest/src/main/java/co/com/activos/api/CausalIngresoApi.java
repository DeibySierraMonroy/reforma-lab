package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.causalingreso.CausalIngreso;
import co.com.activos.usecase.CausalIngresoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public Mono<ApiResponse<CausalIngreso>> findById(@PathVariable Long id, HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("findById start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        
        return useCase.findById(id)
                .map(causal -> ApiResponse.success(causal, request.getRequestURI(), traceId));
    }

    @GetMapping
    public Mono<ApiResponse<List<CausalIngreso>>> findAll(HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("findAll start traceId={} uri={}", traceId, request.getRequestURI());
        
        return useCase.findAll()
                .collectList()
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ApiResponse<CausalIngreso>> create(
            @Valid @RequestBody CausalIngreso causalIngreso,
            HttpServletRequest request) {
                
        final String traceId = (String) request.getAttribute("traceId");
        log.info("create start traceId={} uri={} causalIngreso={}", traceId, request.getRequestURI(), causalIngreso);
        
        return useCase.save(causalIngreso)
                .map(saved -> ApiResponse.success(saved, request.getRequestURI(), traceId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<CausalIngreso>> update(
            @Valid @RequestBody CausalIngreso causalIngreso,
            HttpServletRequest request) {
                
        final String traceId = (String) request.getAttribute("traceId");
        log.info("update start traceId={} uri={} causalIngreso={}", traceId, request.getRequestURI(), causalIngreso);
        
        return useCase.update(causalIngreso)
                .map(updated -> ApiResponse.success(updated, request.getRequestURI(), traceId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ApiResponse<Void>> delete(@PathVariable Long id, HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("delete start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        
        return useCase.deleteById(id)
                .thenReturn(ApiResponse.success(null, request.getRequestURI(), traceId));
    }
}
