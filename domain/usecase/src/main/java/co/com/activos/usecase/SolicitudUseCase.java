package co.com.activos.usecase;

import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import co.com.activos.model.solicitud.repository.SolicitudRepository;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Date;

@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudRepository solicitudRepository;

    public Mono<List<Solicitud>> listAll() {
        return solicitudRepository.listAll();
    }

    public Mono<Solicitud> findById(String idSolicitud) {
        return solicitudRepository.getById(idSolicitud)
                .switchIfEmpty(Mono.error(new BusinessException(
                        ErrorCode.NOT_FOUND,
                        "Solicitud not found with id=" + idSolicitud
                )));
    }

    public Mono<List<Solicitud>> list(Integer page, Integer size, String estado, Date fechaInicio, Date fechaFin) {
        int p = page == null ? 0 : Math.max(0, page);
        int s = size == null ? 20 : Math.max(1, size);
        if (estado != null && !estado.isBlank() && fechaInicio != null && fechaFin != null) {
            return solicitudRepository.findByEstadoAndFechaCreacionBetween(estado, fechaInicio, fechaFin, p, s);
        }
        if (estado != null && !estado.isBlank()) {
            return solicitudRepository.findByEstado(estado, p, s);
        }
        if (fechaInicio != null && fechaFin != null) {
            return solicitudRepository.findByFechaCreacionBetween(fechaInicio, fechaFin, p, s);
        }
        return solicitudRepository.findAll(p, s);
    }

    public Mono<SolicitudDetalle> findDetalleById(String idMesa) {
        return solicitudRepository.getDetalleById(idMesa)
                .switchIfEmpty(Mono.error(new BusinessException(
                        ErrorCode.NOT_FOUND,
                        "Solicitud detail not found with id=" + idMesa
                )));
    }
}
