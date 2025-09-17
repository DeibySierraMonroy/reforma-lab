package co.com.activos.usecase;

import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.repository.MesaSolicitudDetalleRepository;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class MesaSolicitudDetalleUseCase {

    private final MesaSolicitudDetalleRepository mesaSolicitudDetalleRepository;

    public Flux<MesaSolicitudDetalle> buscarPorIdMesaPersonal(String idMesaPersonal) {
        return mesaSolicitudDetalleRepository.buscarPorIdMesaPersonal(idMesaPersonal);
    }
}
