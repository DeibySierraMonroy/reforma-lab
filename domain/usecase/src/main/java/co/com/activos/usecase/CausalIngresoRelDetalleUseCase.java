package co.com.activos.usecase;

import co.com.activos.model.causalingresoreldetalle.CausalIngresoRelDetalle;
import co.com.activos.model.causalingresoreldetalle.gateway.CausalIngresoRelDetalleRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CausalIngresoRelDetalleUseCase {

    private final CausalIngresoRelDetalleRepository repository;

    public Flux<CausalIngresoRelDetalle> findAll() {
        return repository.findAll();
    }

    public Mono<CausalIngresoRelDetalle> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<CausalIngresoRelDetalle> save(CausalIngresoRelDetalle causalIngresoRelDetalle) {
        return repository.save(causalIngresoRelDetalle);
    }

    public Mono<CausalIngresoRelDetalle> update(CausalIngresoRelDetalle causalIngresoRelDetalle) {
        return repository.update(causalIngresoRelDetalle);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
