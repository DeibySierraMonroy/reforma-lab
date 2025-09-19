package co.com.activos.jpa.contratocausalingreso;

import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;
import co.com.activos.model.contratocausalingreso.gateway.ContratoCausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Repository
@RequiredArgsConstructor
public class ContratoCausalIngresoRepositoryAdapter implements ContratoCausalIngresoRepository {

    private final ContratoCausalIngresoDataRepository repository;

    @Override
    public Flux<ContratoCausalIngreso> findAll() {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
                .map(ContratoCausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ContratoCausalIngreso> findById(Long id) {
        return Mono.defer(() -> Mono.justOrEmpty(repository.findById(id)))
                .map(ContratoCausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ContratoCausalIngreso> save(ContratoCausalIngreso contratoCausalIngreso) {
        return Mono.fromCallable(() -> {
                ContratoCausalIngresoData data = ContratoCausalIngresoMapper.toData(contratoCausalIngreso);
                return repository.save(data);
            })
            .map(ContratoCausalIngresoMapper::toDomain)
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ContratoCausalIngreso> update(ContratoCausalIngreso contratoCausalIngreso) {
        return Mono.fromCallable(() -> {
                if (contratoCausalIngreso.getIdContratoCausalIngreso() == null) {
                    throw new IllegalArgumentException("ID is required for update");
                }
                if (!repository.existsById(contratoCausalIngreso.getIdContratoCausalIngreso())) {
                    throw new RuntimeException("ContratoCausalIngreso not found with id: " + contratoCausalIngreso.getIdContratoCausalIngreso());
                }
                ContratoCausalIngresoData data = ContratoCausalIngresoMapper.toData(contratoCausalIngreso);
                return repository.save(data);
            })
            .map(ContratoCausalIngresoMapper::toDomain)
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return Mono.fromRunnable(() -> repository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
