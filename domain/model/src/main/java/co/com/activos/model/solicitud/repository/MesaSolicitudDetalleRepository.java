package co.com.activos.model.solicitud.repository;

import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.busqueda.MesaSolicitudCriteria;
import reactor.core.publisher.Flux;

public interface MesaSolicitudDetalleRepository {
    Flux<MesaSolicitudDetalle> buscarPorIdMesaPersonal(String idMesaPersonal);
    Flux<MesaSolicitudDetalle> buscar(MesaSolicitudCriteria mesaSolicitudCriteria);
}
