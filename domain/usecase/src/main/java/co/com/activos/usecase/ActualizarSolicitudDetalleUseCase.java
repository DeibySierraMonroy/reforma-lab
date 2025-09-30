package co.com.activos.usecase;

import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudDetalle;
import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudRepository;
import lombok.RequiredArgsConstructor;


import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class ActualizarSolicitudDetalleUseCase {

    private final ActualizarSolicitudRepository repository;

    public Mono<ActualizarSolicitudDetalle> actualizar(ActualizarSolicitudDetalle solicitud) {
        return repository.actualizar(solicitud);
    }
}
