package co.com.activos.jpa.causalingreso;

import co.com.activos.model.causalingreso.CausalIngreso;
import co.com.activos.model.causalingreso.gateway.CausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@RequiredArgsConstructor
public class CausalIngresoDataRepositoryAdapter implements CausalIngresoRepository {

    private final CausalIngresoDataRepository repository;

    @Override
    public Mono<CausalIngreso> findById(Long id) {
        return Mono.fromCallable(() -> repository.findById(id))
                .flatMap(optional -> optional.map(entity -> Mono.just(CausalIngresoMapper.toDomain(entity)))
                        .orElseGet(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<CausalIngreso> findAll() {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
                .map(CausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CausalIngreso> save(CausalIngreso causalIngreso) {
        return Mono.fromCallable(() -> {
                    CausalIngresoEntity entity = CausalIngresoMapper.toData(causalIngreso);
                    return repository.save(entity);
                })
                .map(CausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CausalIngreso> update(CausalIngreso causalIngreso) {
        return Mono.fromCallable(() -> {
                    CausalIngresoEntity entity = CausalIngresoMapper.toData(causalIngreso);
                    return repository.save(entity);
                })
                .map(CausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return Mono.fromRunnable(() -> repository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
