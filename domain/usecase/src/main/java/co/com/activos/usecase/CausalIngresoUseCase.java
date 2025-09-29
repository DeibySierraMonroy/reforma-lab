package co.com.activos.usecase;


import co.com.activos.model.parametrizacionCausales.causalIngreso.CausalIngreso;
import co.com.activos.model.parametrizacionCausales.causalIngreso.gateway.CausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class CausalIngresoUseCase {

    private final CausalIngresoRepository repository;

    public Flux<CausalIngreso> findByAllForStatus() {
        return repository.findByAllForStatus("A");
    }

}
