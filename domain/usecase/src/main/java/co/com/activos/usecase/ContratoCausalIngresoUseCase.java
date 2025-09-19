package co.com.activos.usecase;

import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;
import co.com.activos.model.contratocausalingreso.gateway.ContratoCausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ContratoCausalIngresoUseCase {

    private final ContratoCausalIngresoRepository repository;

    public Flux<ContratoCausalIngreso> findAll() {
        return repository.findAll();
    }

    public Mono<ContratoCausalIngreso> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<ContratoCausalIngreso> save(ContratoCausalIngreso contrato) {
        return repository.save(contrato);
    }

    public Mono<ContratoCausalIngreso> update(ContratoCausalIngreso contrato) {
        return repository.update(contrato);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

}

