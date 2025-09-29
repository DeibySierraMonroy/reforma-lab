package co.com.activos.model.parametrizacionCausales.causalIngreso.gateway;

import co.com.activos.model.parametrizacionCausales.causalIngreso.CausalIngreso;
import reactor.core.publisher.Flux;

public interface CausalIngresoRepository {
    Flux<CausalIngreso> findByAllForStatus(String status);
}
