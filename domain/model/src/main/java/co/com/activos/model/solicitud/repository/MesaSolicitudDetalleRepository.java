package co.com.activos.model.solicitud.repository;

import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import reactor.core.publisher.Flux;

public interface MesaSolicitudDetalleRepository {
    Flux<MesaSolicitudDetalle> buscarPorIdMesaPersonal(String idMesaPersonal);
}
