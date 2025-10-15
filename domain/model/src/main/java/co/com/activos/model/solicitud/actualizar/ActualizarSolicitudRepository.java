package co.com.activos.model.solicitud.actualizar;

import reactor.core.publisher.Mono;

public interface ActualizarSolicitudRepository {

    Mono<ActualizarSolicitudDetalle> actualizar(ActualizarSolicitudDetalle solicitud);

}
