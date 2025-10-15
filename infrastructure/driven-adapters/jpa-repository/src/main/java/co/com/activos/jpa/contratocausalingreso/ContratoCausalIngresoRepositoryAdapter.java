package co.com.activos.jpa.contratocausalingreso;

import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
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
                        throw new IllegalArgumentException("ID es requerido para realizar actualizacion");
                    }
                    if (!repository.existsById(contratoCausalIngreso.getIdContratoCausalIngreso())) {
                        throw new BusinessException(ErrorCode.NOT_FOUND, "No existe la parametrizacion :  " + contratoCausalIngreso.getIdContratoCausalIngreso());
                    }
                    ContratoCausalIngresoData data = ContratoCausalIngresoMapper.toData(contratoCausalIngreso);
                    return repository.save(data);
                })
                .map(ContratoCausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ContratoCausalIngreso> validarExistencia(ContratoCausalIngreso contratoCausalIngreso) {
        return Mono.defer(() ->
                    Mono.justOrEmpty(repository
                            .findByTdcTdAndEmpNdAndTdcTdFilAndEmpNdFilAndTdcTdEplAndEplNd(
                                    contratoCausalIngreso.getTdcTd(),
                                    contratoCausalIngreso.getEmpNd(),
                                    contratoCausalIngreso.getTdcTdFil(),
                                    contratoCausalIngreso.getEmpNdFil(),
                                    contratoCausalIngreso.getTdcTdEpl(),
                                    contratoCausalIngreso.getEplNd()))
                            .map(ContratoCausalIngresoMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic()));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return Mono.fromRunnable(() -> repository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
