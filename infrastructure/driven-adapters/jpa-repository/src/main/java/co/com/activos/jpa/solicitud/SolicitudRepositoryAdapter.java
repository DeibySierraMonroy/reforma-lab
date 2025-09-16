package co.com.activos.jpa.solicitud;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.repository.SolicitudRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Date;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Repository
public class SolicitudRepositoryAdapter extends AdapterOperations<Solicitud, SolicitudData, String, SolicitudDataRepository>
        implements SolicitudRepository {

    protected SolicitudRepositoryAdapter(SolicitudDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Solicitud.SolicitudBuilder.class).build());
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
