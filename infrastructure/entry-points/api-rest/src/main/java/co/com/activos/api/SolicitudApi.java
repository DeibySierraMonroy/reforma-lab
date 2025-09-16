package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.api.model.SolicitudListParams;
import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.usecase.SolicitudUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
