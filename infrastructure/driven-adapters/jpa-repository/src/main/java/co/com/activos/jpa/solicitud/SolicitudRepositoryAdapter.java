package co.com.activos.jpa.solicitud;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.jpa.helper.SpecificationUtils;
import co.com.activos.jpa.mesa.MesaSolicitudDetalleData;
import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.SolicitudDetalle;
import co.com.activos.model.solicitud.SolicitudPersonal;
import co.com.activos.model.solicitud.busqueda.SolicitudCriteria;
import co.com.activos.model.solicitud.repository.SolicitudRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashSet;
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
                .nombreTemporal(d.getNombreTemporal())
                .nombreUsuaria(d.getNombreUsuaria())
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
        data.setNombreTemporal(entity.nombreTemporal());
        data.setNombreUsuaria(entity.nombreUsuaria());
        return data;
    }

    @Override
    public Mono<List<Solicitud>> listAll() {
        return Mono.fromCallable(super::findAll);
    }

    @Override
    public Mono<List<Solicitud>> buscarSolicitud(SolicitudCriteria solicitudCriteria) {
        return Mono.fromCallable(() -> {
            Specification<SolicitudData> spec =
                    SpecificationUtils.buildFromDto(solicitudCriteria, SolicitudData.class);
            return repository.findAll(spec)
                    .stream()
                    .map(this::toEntity)
                    .toList();
        });
    }


    @Override
    public Mono<Solicitud> getById(String idSolicitud) {
        return Mono.fromCallable(() -> super.findById(idSolicitud))
                .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Mono<SolicitudDetalle> getDetalleById(String idSolicitud) {
        return Mono.fromCallable(() -> repository.findWithPersonalById(idSolicitud))
                .flatMap(Mono::justOrEmpty)
                .flatMap(data -> {
                    boolean empty = data.getPersonal() == null || data.getPersonal().isEmpty();
                    if (!empty) return Mono.just(data);
                    return Mono.fromCallable(() -> {
                        List<SolicitudPersonalData> childs = personalRepository.findBySolicitud_IdSolicitud(idSolicitud);
                        if (childs != null && !childs.isEmpty()) {
                            Set<SolicitudPersonalData> set = new HashSet<>(childs);
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
                .nombreTemporal(d.getNombreTemporal())
                .tdcTdUsuaria(d.getTdcTdUsuaria())
                .empNdUsuaria(d.getEmpNdUsuaria())
                .nombreUsuaria(d.getNombreUsuaria())
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
                        .estadoPersona(p.getEstadoPersona())
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
        Pageable pageable = PageRequest.of(page, size);
        return Mono.fromCallable(() -> repository.findByEstadoSolicitudAndFechaCreacionBetween(estadoSolicitud, fechaInicio, fechaFin, pageable))
                .map(pageResult -> pageResult.getContent().stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Mono<SolicitudPersonal> updateSolicitudPersonal(SolicitudPersonal solicitudPersonal) {
        return Mono.fromCallable(() -> personalRepository.findById(solicitudPersonal.idMesaPersonal()))
                .flatMap(optionalPersonal -> {
                    if (optionalPersonal.isEmpty()) {
                        return Mono.error(new RuntimeException("SolicitudPersonal not found with id: " + solicitudPersonal.idMesaPersonal()));
                    }

                    SolicitudPersonalData personalData = optionalPersonal.get();

                    // Update all fields from the request
                    personalData.setPrimerApellido(solicitudPersonal.primerApellido());
                    personalData.setSegundoApellido(solicitudPersonal.segundoApellido());
                    personalData.setPrimerNombre(solicitudPersonal.primerNombre());
                    personalData.setSegundoNombre(solicitudPersonal.segundoNombre());
                    personalData.setTipoDocumento(solicitudPersonal.tipoDocumento());
                    personalData.setNumeroDocumento(solicitudPersonal.numeroDocumento());
                    personalData.setTelefonoContacto(solicitudPersonal.telefonoContacto());
                    personalData.setCargo(solicitudPersonal.cargo());
                    personalData.setEstadoPersona(solicitudPersonal.estadoPersona());

                    personalData = personalRepository.save(personalData);

                    // Convert back to domain model with all fields
                    return Mono.just(SolicitudPersonal.builder()
                            .idMesaPersonal(personalData.getIdMesaPersonal())
                            .primerApellido(personalData.getPrimerApellido())
                            .segundoApellido(personalData.getSegundoApellido())
                            .primerNombre(personalData.getPrimerNombre())
                            .segundoNombre(personalData.getSegundoNombre())
                            .tipoDocumento(personalData.getTipoDocumento())
                            .numeroDocumento(personalData.getNumeroDocumento())
                            .telefonoContacto(personalData.getTelefonoContacto())
                            .cargo(personalData.getCargo())
                            .estadoPersona(personalData.getEstadoPersona())
                            .build());
                });
    }
}
