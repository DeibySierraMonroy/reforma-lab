package co.com.activos.api;

import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;
import co.com.activos.usecase.ContratoCausalIngresoUseCase;
import co.com.activos.api.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contratos-causales-ingreso")
@Validated
public class ContratoCausalIngresoApi {

    private final ContratoCausalIngresoUseCase contratoCausalIngresoUseCase;

    @GetMapping
    public Mono<ApiResponse<List<ContratoCausalIngreso>>> listAll(HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("listAll start traceId={} uri={}", traceId, request.getRequestURI());
        return contratoCausalIngresoUseCase.findAll()
                .collectList()
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

    @GetMapping("/{id}")
    public Mono<ApiResponse<ContratoCausalIngreso>> getById(
            @PathVariable @Positive(message = "ID must be > 0") Long id,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("getById start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        return contratoCausalIngresoUseCase.findById(id)
                .map(contrato -> ApiResponse.success(contrato, request.getRequestURI(), traceId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ApiResponse<ContratoCausalIngreso>> create(
            @Valid @RequestBody ContratoCausalIngreso contrato,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("create start traceId={} body={} uri={}", traceId, contrato, request.getRequestURI());
        return contratoCausalIngresoUseCase.save(contrato)
                .map(saved -> ApiResponse.success(saved, request.getRequestURI(), traceId));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<ContratoCausalIngreso>> update(
            @PathVariable @Positive(message = "ID must be > 0") Long id,
            @Valid @RequestBody ContratoCausalIngreso contrato,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("update start traceId={} id={} body={} uri={}", traceId, id, contrato, request.getRequestURI());
        contrato.setIdContratoCausalIngreso(id);
        return contratoCausalIngresoUseCase.update(contrato)
                .map(updated -> ApiResponse.success(updated, request.getRequestURI(), traceId));
    }

    @DeleteMapping("/{id}")
    public Mono<ApiResponse<String>> delete(
            @PathVariable @Positive(message = "ID must be > 0") Long id,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("delete start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        return contratoCausalIngresoUseCase.deleteById(id)
                .thenReturn(ApiResponse.success("deleted", request.getRequestURI(), traceId));
    }
}
