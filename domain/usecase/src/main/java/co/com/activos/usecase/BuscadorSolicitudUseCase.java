package co.com.activos.usecase;

import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.busqueda.MesaSolicitudCriteria;
import co.com.activos.model.solicitud.repository.MesaSolicitudDetalleRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class BuscadorSolicitudUseCase {

    private final MesaSolicitudDetalleRepository mesaSolicitudDetalleRepository;

    public Flux<MesaSolicitudDetalle> buscar(MesaSolicitudCriteria criteria) {
        return mesaSolicitudDetalleRepository.buscar(criteria);
    }


}
