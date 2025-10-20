package co.com.activos.model.contratocausalingreso.gateway;

import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContratoCausalIngresoRepository {
    Flux<ContratoCausalIngreso> findAll();
    Mono<ContratoCausalIngreso> findById(Long id);
    Mono<ContratoCausalIngreso> save(ContratoCausalIngreso contratoCausalIngreso);
    Mono<ContratoCausalIngreso> update(ContratoCausalIngreso contratoCausalIngreso);
    Mono<ContratoCausalIngreso> validarExistencia(ContratoCausalIngreso contratoCausalIngreso);
    Mono<Void> deleteById(Long id);
}
