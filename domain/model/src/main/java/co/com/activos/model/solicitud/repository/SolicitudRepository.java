package co.com.activos.model.solicitud.repository;

import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import co.com.activos.model.solicitud.SolicitudPersonal;
import co.com.activos.model.solicitud.busqueda.SolicitudCriteria;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Date;

public interface SolicitudRepository {
    Mono<List<Solicitud>> listAll();
    Mono<List<Solicitud>> buscarSolicitud(SolicitudCriteria solicitudCriteria);
    Mono<Solicitud> getById(String idSolicitud);
    Mono<SolicitudDetalle> getDetalleById(String idSolicitud);
    Mono<SolicitudPersonal> updateSolicitudPersonal(SolicitudPersonal solicitudPersonal);
    Mono<List<Solicitud>> findAll(int page, int size);
    Mono<List<Solicitud>> findByEstado(String estadoSolicitud, int page, int size);
    Mono<List<Solicitud>> findByFechaCreacionBetween(Date fechaInicio, Date fechaFin, int page, int size);
    Mono<List<Solicitud>> findByEstadoAndFechaCreacionBetween(String estadoSolicitud, Date fechaInicio, Date fechaFin, int page, int size);
}
