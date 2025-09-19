package co.com.activos.usecase;

import co.com.activos.model.causalingreso.CausalIngreso;
import co.com.activos.model.causalingreso.gateway.CausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CausalIngresoUseCase {

    private final CausalIngresoRepository repository;

    public Mono<CausalIngreso> findById(Long id) {
        return repository.findById(id);
    }

    public Flux<CausalIngreso> findAll() {
        return repository.findAll();
    }

    public Mono<CausalIngreso> save(CausalIngreso causalIngreso) {
        return repository.save(causalIngreso);
    }

    public Mono<CausalIngreso> update(CausalIngreso causalIngreso) {
        return repository.update(causalIngreso);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
