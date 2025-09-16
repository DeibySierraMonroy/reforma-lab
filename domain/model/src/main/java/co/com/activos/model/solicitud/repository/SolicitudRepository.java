package co.com.activos.model.solicitud.repository;

import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Date;

public interface SolicitudRepository {
    Mono<List<Solicitud>> listAll();
    Mono<Solicitud> getById(String idSolicitud);
    Mono<SolicitudDetalle> getDetalleById(String idSolicitud);

    // Paginadas y filtradas
    Mono<List<Solicitud>> findAll(int page, int size);
    Mono<List<Solicitud>> findByEstado(String estadoSolicitud, int page, int size);
    Mono<List<Solicitud>> findByFechaCreacionBetween(Date fechaInicio, Date fechaFin, int page, int size);
    Mono<List<Solicitud>> findByEstadoAndFechaCreacionBetween(String estadoSolicitud, Date fechaInicio, Date fechaFin, int page, int size);
}
