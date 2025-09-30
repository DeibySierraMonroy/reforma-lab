package co.com.activos.model.solicitud.actualizar;

import co.com.activos.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface ActualizarSolicitudRepository {

    Mono<ActualizarSolicitudDetalle> actualizar(ActualizarSolicitudDetalle solicitud);

}
