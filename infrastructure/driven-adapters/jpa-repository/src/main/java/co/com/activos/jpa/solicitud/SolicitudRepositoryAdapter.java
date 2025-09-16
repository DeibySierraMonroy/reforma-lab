package co.com.activos.jpa.solicitud;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import co.com.activos.model.solicitud.SolicitudPersonal;
import co.com.activos.model.solicitud.repository.SolicitudRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Date;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class SolicitudRepositoryAdapter extends AdapterOperations<Solicitud, SolicitudData, String, SolicitudDataRepository>
        implements SolicitudRepository {

    private final SolicitudPersonalDataRepository personalRepository;

    protected SolicitudRepositoryAdapter(SolicitudDataRepository repository, ObjectMapper mapper,
                                         SolicitudPersonalDataRepository personalRepository) {
        super(repository, mapper, d -> Solicitud.builder()
                .idMesa(d.getIdSolicitud())
                .tdcTdTemporal(d.getTdcTdTemporal())
                .empNdTemporal(d.getEmpNdTemporal())
                .tdcTdUsuaria(d.getTdcTdUsuaria())
                .empNdUsuaria(d.getEmpNdUsuaria())
                .estadoSolicitud(d.getEstadoSolicitud())
                .fechaCreacion(d.getFechaCreacion())
                .build());
        this.personalRepository = personalRepository;
    }

    @Override
    protected SolicitudData toData(Solicitud entity) {
        SolicitudData data = new SolicitudData();
        data.setIdSolicitud(entity.idMesa());
        data.setTdcTdTemporal(entity.tdcTdTemporal());
        data.setEmpNdTemporal(entity.empNdTemporal());
        data.setTdcTdUsuaria(entity.tdcTdUsuaria());
        data.setEmpNdUsuaria(entity.empNdUsuaria());
        data.setEstadoSolicitud(entity.estadoSolicitud());
        data.setFechaCreacion(entity.fechaCreacion());
        return data;
    }

    @Override
    public Mono<List<Solicitud>> listAll() {
        return Mono.fromCallable(super::findAll);
    }

    @Override
    public Mono<Solicitud> getById(String idSolicitud) {
        return Mono.fromCallable(() -> super.findById(idSolicitud))
                .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Mono<SolicitudDetalle> getDetalleById(String idSolicitud) {
        return Mono.fromCallable(() -> repository.findWithPersonalById(idSolicitud))
                .flatMap(optional -> Mono.justOrEmpty(optional))
                .flatMap(data -> {
                    boolean empty = data.getPersonal() == null || data.getPersonal().isEmpty();
                    if (!empty) return Mono.just(data);
                    return Mono.fromCallable(() -> {
                        List<SolicitudPersonalData> childs = personalRepository.findBySolicitud_IdSolicitud(idSolicitud);
                        if (childs != null && !childs.isEmpty()) {
                            Set<SolicitudPersonalData> set = childs.stream().collect(Collectors.toSet());
                            data.setPersonal(set);
                        }
                        return data;
                    });
                })
                .map(this::toDetalle);
    }

    private SolicitudDetalle toDetalle(SolicitudData d) {
        Solicitud solicitud = Solicitud.builder()
                .idMesa(d.getIdSolicitud())
                .tdcTdTemporal(d.getTdcTdTemporal())
                .empNdTemporal(d.getEmpNdTemporal())
                .tdcTdUsuaria(d.getTdcTdUsuaria())
                .empNdUsuaria(d.getEmpNdUsuaria())
                .estadoSolicitud(d.getEstadoSolicitud())
                .fechaCreacion(d.getFechaCreacion())
                .build();

        List<SolicitudPersonal> personal = d.getPersonal() == null ? List.of() : d.getPersonal().stream()
                .map(p -> SolicitudPersonal.builder()
                        .idMesaPersonal(p.getIdMesaPersonal())
                        .primerApellido(p.getPrimerApellido())
                        .segundoApellido(p.getSegundoApellido())
                        .primerNombre(p.getPrimerNombre())
                        .segundoNombre(p.getSegundoNombre())
                        .tipoDocumento(p.getTipoDocumento())
                        .numeroDocumento(p.getNumeroDocumento())
                        .telefonoContacto(p.getTelefonoContacto())
                        .cargo(p.getCargo())
                        .causaOrigina(p.getCausaOrigina())
                        .detalleOrigina(p.getDetalleOrigina())
                        .especificaCausa(p.getEspecificaCausa())
                        .cargoReemplaza(p.getCargoReemplaza())
                        .nombreReemplaza(p.getNombreReemplaza())
                        .fechaFinReemplaza(p.getFechaFinReemplaza())
                        .fechaFinCausal(p.getFechaFinCausal())
                        .observaciones(p.getObservaciones())
                        .ciudadTrabajo(p.getCiudadTrabajo())
                        .sucursal(p.getSucursal())
                        .centroCosto(p.getCentroCosto())
                        .puntoVenta(p.getPuntoVenta())
                        .modalidadTrabajo(p.getModalidadTrabajo())
                        .fechaIngreso(p.getFechaIngreso())
                        .nivelRiesgo(p.getNivelRiesgo())
                        .salario(p.getSalario())
                        .modalidadSalario(p.getModalidadSalario())
                        .horasAlMes(p.getHorasAlMes())
                        .valorHora(p.getValorHora())
                        .valorDia(p.getValorDia())
                        .trabajaSabados(p.getTrabajaSabados())
                        .diaDescanso(p.getDiaDescanso())
                        .pagoSubsidioTransporte(p.getPagoSubsidioTransporte())
                        .tipoAuxilio(p.getTipoAuxilio())
                        .otroAuxilio(p.getOtroAuxilio())
                        .valorAuxilio(p.getValorAuxilio())
                        .comision(p.getComision())
                        .tipoComision(p.getTipoComision())
                        .pagoGarantizado(p.getPagoGarantizado())
                        .mesesPagoGarantizado(p.getMesesPagoGarantizado())
                        .observacionPagos(p.getObservacionPagos())
                        .horaPresentacion(p.getHoraPresentacion())
                        .lugarPresentacion(p.getLugarPresentacion())
                        .telefonoPresentacion(p.getTelefonoPresentacion())
                        .nombrePresentacion(p.getNombrePresentacion())
                        .cargoPresentacion(p.getCargoPresentacion())
                        .linkPresentacion(p.getLinkPresentacion())
                        .build())
                .toList();

        return SolicitudDetalle.builder()
                .solicitud(solicitud)
                .personal(personal)
                .build();
    }

    @Override
    public Mono<List<Solicitud>> findAll(int page, int size) {
        return Mono.fromCallable(() -> {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findAllBy(pageable).getContent();
        }).map(this::toList);
    }

    @Override
    public Mono<List<Solicitud>> findByEstado(String estadoSolicitud, int page, int size) {
        return Mono.fromCallable(() -> {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findByEstadoSolicitud(estadoSolicitud, pageable).getContent();
        }).map(this::toList);
    }

    @Override
    public Mono<List<Solicitud>> findByFechaCreacionBetween(Date fechaInicio, Date fechaFin, int page, int size) {
        return Mono.fromCallable(() -> {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findByFechaCreacionBetween(fechaInicio, fechaFin, pageable).getContent();
        }).map(this::toList);
    }

    @Override
    public Mono<List<Solicitud>> findByEstadoAndFechaCreacionBetween(String estadoSolicitud, Date fechaInicio, Date fechaFin, int page, int size) {
        return Mono.fromCallable(() -> {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findByEstadoSolicitudAndFechaCreacionBetween(estadoSolicitud, fechaInicio, fechaFin, pageable).getContent();
        }).map(this::toList);
    }
}
