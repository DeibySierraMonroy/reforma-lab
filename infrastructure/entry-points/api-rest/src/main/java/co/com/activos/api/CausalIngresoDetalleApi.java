package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.api.model.CausalIngresoDetalleRequest;
import co.com.activos.model.causal.CausalIngresoDetalle;
import co.com.activos.usecase.CausalIngresoDetalleUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class CausalIngresoDetalleApi {

    private final CausalIngresoDetalleUseCase causalIngresoDetalleUseCase;

    @GetMapping(path = "/causales-ingreso-detalle")
    public Mono<ApiResponse<List<CausalIngresoDetalle>>> listCausales(HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("listCausales start traceId={} uri={}", traceId, request.getRequestURI());
        return causalIngresoDetalleUseCase.listAll()
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/causales-ingreso-detalle/{id}")
    public Mono<ApiResponse<CausalIngresoDetalle>> getCausal(@PathVariable("id") @Positive(message = "id must be > 0") Long id,
                                                             HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("getCausal start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        return causalIngresoDetalleUseCase.getById(id)
                .map(causal -> ApiResponse.success(causal, request.getRequestURI(), traceId));
    }

    @PostMapping(path = "/causales-ingreso-detalle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<CausalIngresoDetalle>> createCausal(@Valid @RequestBody CausalIngresoDetalleRequest requestBody,
                                                               HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("createCausal start traceId={} body={} uri={}", traceId, requestBody, request.getRequestURI());
        CausalIngresoDetalle causal = CausalIngresoDetalle.builder()
                .idCausalIngresoDet(requestBody.getIdCausalIngresoDet())
                .descCausalIngresoDet(requestBody.getDescCausalIngresoDet())
                .estado(requestBody.getEstado())
                .audUsuario(requestBody.getAudUsuario())
                .audFecha(requestBody.getAudFecha())
                .build();
        return causalIngresoDetalleUseCase.create(causal)
                .map(saved -> ApiResponse.success(saved, request.getRequestURI(), traceId));
    }

    @PutMapping(path = "/causales-ingreso-detalle/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<CausalIngresoDetalle>> updateCausal(@PathVariable("id") @Positive(message = "id must be > 0") Long id,
                                                                @Valid @RequestBody CausalIngresoDetalleRequest requestBody,
                                                                HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("updateCausal start traceId={} id={} body={} uri={}", traceId, id, requestBody, request.getRequestURI());
        CausalIngresoDetalle causal = CausalIngresoDetalle.builder()
                .idCausalIngresoDet(requestBody.getIdCausalIngresoDet() != null ? requestBody.getIdCausalIngresoDet() : id)
                .descCausalIngresoDet(requestBody.getDescCausalIngresoDet())
                .estado(requestBody.getEstado())
                .audUsuario(requestBody.getAudUsuario())
                .audFecha(requestBody.getAudFecha())
                .build();
        return causalIngresoDetalleUseCase.update(id, causal)
                .map(updated -> ApiResponse.success(updated, request.getRequestURI(), traceId));
    }

    @DeleteMapping(path = "/causales-ingreso-detalle/{id}")
    public Mono<ApiResponse<String>> deleteCausal(@PathVariable("id") @Positive(message = "id must be > 0") Long id,
                                                  HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("deleteCausal start traceId={} id={} uri={}", traceId, id, request.getRequestURI());
        return causalIngresoDetalleUseCase.delete(id)
                .thenReturn(ApiResponse.success("deleted", request.getRequestURI(), traceId));
    }
}
