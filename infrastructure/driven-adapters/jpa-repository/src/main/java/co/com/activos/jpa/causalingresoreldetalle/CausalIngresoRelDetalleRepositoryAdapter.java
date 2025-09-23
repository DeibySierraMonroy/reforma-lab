package co.com.activos.jpa.causalingresoreldetalle;

import co.com.activos.model.causalingresoreldetalle.CausalIngresoRelDetalle;
import co.com.activos.model.causalingresoreldetalle.gateway.CausalIngresoRelDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CausalIngresoRelDetalleRepositoryAdapter implements CausalIngresoRelDetalleRepository {

    private final ICausalIngresoRelDetalleJpaRepository repository;

    @Override
    public Mono<CausalIngresoRelDetalle> findById(Long id) {
        return Mono.fromCallable(() -> repository.findById(id))
                .flatMap(optional -> optional.map(entity -> Mono.just(CausalIngresoRelDetalleMapper.toDomain(entity)))
                        .orElseGet(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<CausalIngresoRelDetalle> findAll() {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
                .map(CausalIngresoRelDetalleMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CausalIngresoRelDetalle> save(CausalIngresoRelDetalle causalIngresoRelDetalle) {
        return Mono.fromCallable(() -> {
                    CausalIngresoRelDetalleData data = CausalIngresoRelDetalleMapper.toData(causalIngresoRelDetalle);
                    return repository.save(data);
                })
                .map(CausalIngresoRelDetalleMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CausalIngresoRelDetalle> update(CausalIngresoRelDetalle causalIngresoRelDetalle) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return Mono.fromRunnable(() -> repository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    @Override
    public Mono<List<CausalIngresoRelDetalle>> findParametrizadas(Long idCausalIngreso) {
        return Mono.fromCallable(() -> repository.findByIdCausalIngreso(idCausalIngreso))
                .map(list -> list.stream()
                        .map(CausalIngresoRelDetalleMapper::toDomain)
                        .toList());
    }
}
