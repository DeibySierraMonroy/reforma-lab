package co.com.activos.jpa.causalingreso;


import co.com.activos.model.parametrizacionCausales.causalIngreso.CausalIngreso;
import co.com.activos.model.parametrizacionCausales.causalIngreso.gateway.CausalIngresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class CausalIngresoDataRepositoryAdapter implements CausalIngresoRepository {

    private final CausalIngresoDataRepository repository;


    @Override
    public Flux<CausalIngreso> findByAllForStatus(String status) {
        return Flux.fromIterable(repository.findByEstado(status)
                .stream()
                .map(CausalIngresoMapper::toDomain)
                .toList());
    }

}
