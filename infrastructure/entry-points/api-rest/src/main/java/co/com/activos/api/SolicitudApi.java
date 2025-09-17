package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.api.model.ErrorDetail;
import co.com.activos.api.model.SolicitudListParams;
import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.solicitud.SolicitudPersonal;
import co.com.activos.usecase.SolicitudUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class SolicitudApi {

    private final SolicitudUseCase solicitudUseCase;

    @GetMapping(path = "/listarSolicitudes")
    public Mono<ApiResponse<List<Solicitud>>> listarSolicitudes(@Valid SolicitudListParams params,
                                                                HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("listarSolicitudes start traceId={} uri={} params={}", traceId, request.getRequestURI(), params);

        return solicitudUseCase.list(params.getPage(), params.getSize(), params.getEstado(),
                        params.getFechaInicioSql(), params.getFechaFinSql())
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/ListarSolicitudes/{id_solicitud}")
    public Mono<ApiResponse<Solicitud>> listarSolicitudPorId(@PathVariable("id_solicitud") String idSolicitud,
                                                             HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("listarSolicitudPorId start traceId={} id_solicitud={} uri={}", traceId, idSolicitud, request.getRequestURI());
        return solicitudUseCase.findById(idSolicitud)
                .map(solicitud -> ApiResponse.success(solicitud, request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/solicitudes/{id}/detalle")
    public Mono<ApiResponse<SolicitudDetalle>> getDetalle(@PathVariable("id") String idMesa, HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("getDetalle start traceId={} id={} uri={}", traceId, idMesa, request.getRequestURI());
        return solicitudUseCase.findDetalleById(idMesa)
                .map(detalle -> ApiResponse.success(detalle, request.getRequestURI(), traceId));
    }

    @PutMapping("/solicitudes/personal/{id}")
    public Mono<ApiResponse<SolicitudPersonal>> actualizarSolicitudPersonal(
            @PathVariable String id,
            @Valid @RequestBody SolicitudPersonal solicitudPersonal,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("actualizarSolicitudPersonal start traceId={} id={}", traceId, id);
        
        return solicitudUseCase.updateSolicitudPersonal(id, solicitudPersonal)
                .map(updated -> ApiResponse.success(updated, request.getRequestURI(), traceId));
    }
}
