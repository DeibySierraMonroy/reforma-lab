package co.com.activos.jpa.mesa;

import co.com.activos.jpa.helper.SpecificationUtils;
import co.com.activos.model.solicitud.busqueda.MesaSolicitudCriteria;
import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.repository.MesaSolicitudDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class MesaSolicitudDetalleRepositoryAdapter implements MesaSolicitudDetalleRepository {

    private final IMesaSolicitudDetalleJpaRepository repository;

    @Override
    public Flux<MesaSolicitudDetalle> buscarPorIdMesaPersonal(String idMesaPersonal) {
        return Flux.fromIterable(
                repository.findByIdMesaPersonal(idMesaPersonal).stream()
                        .map(this::toDomain)
                        .toList());
    }

    @Override
    public Flux<MesaSolicitudDetalle> buscar(MesaSolicitudCriteria criteria) {
        Specification<MesaSolicitudDetalleData> spec =
                SpecificationUtils.buildFromDto(criteria, MesaSolicitudDetalleData.class);

        return Flux.fromIterable(
                repository.findAll(spec)
                        .stream()
                        .map(this::toDomain)
                        .toList()
        );
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
                .nombreTemporal(data.getNombreTemporal())
                .nombreUsuaria(data.getNombreUsuaria())
                .idParametrizacion(data.getIdParametrizacion())
                .observacionCausa(data.getObservacionCausa())
                .build();
    }
}
