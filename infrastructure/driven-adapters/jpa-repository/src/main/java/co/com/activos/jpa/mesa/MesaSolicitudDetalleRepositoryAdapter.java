package co.com.activos.jpa.mesa;

import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.repository.MesaSolicitudDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MesaSolicitudDetalleRepositoryAdapter implements MesaSolicitudDetalleRepository {

    private final IMesaSolicitudDetalleJpaRepository repository;

    @Override
    public Flux<MesaSolicitudDetalle> buscarPorIdMesaPersonal(String idMesaPersonal) {
        return Flux.defer(() -> {
            List<MesaSolicitudDetalleData> dataList = repository.findByIdMesaPersonal(idMesaPersonal);
            List<MesaSolicitudDetalle> result = dataList.stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
            return Flux.fromIterable(result);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private MesaSolicitudDetalle toDomain(MesaSolicitudDetalleData data) {
        return MesaSolicitudDetalle.builder()
                .primerNombre(data.getPrimerNombre())
                .segundoNombre(data.getSegundoNombre())
                .primerApellido(data.getPrimerApellido())
                .segundoApellido(data.getSegundoApellido())
                .tipoDocumento(data.getTipoDocumento())
                .numeroDocumento(data.getNumeroDocumento())
                .telefonoContacto(data.getTelefonoContacto())
                .cargo(data.getCargo())
                .causaOrigina(data.getCausaOrigina())
                .detalleOrigina(data.getDetalleOrigina())
                .especificaCausa(data.getEspecificaCausa())
                .cargoReemplaza(data.getCargoReemplaza())
                .nombreReemplaza(data.getNombreReemplaza())
                .fechaFinReemplaza(data.getFechaFinReemplaza())
                .fechaFinCausal(data.getFechaFinCausal())
                .observaciones(data.getObservaciones())
                .tdcTdTemporal(data.getTdcTdTemporal())
                .empNdTemporal(data.getEmpNdTemporal())
                .tdcTdUsuaria(data.getTdcTdUsuaria())
                .empNdUsuaria(data.getEmpNdUsuaria())
                .idMesa(data.getIdMesa())
                .idMesaPersonal(data.getIdMesaPersonal())
                .build();
    }
}
