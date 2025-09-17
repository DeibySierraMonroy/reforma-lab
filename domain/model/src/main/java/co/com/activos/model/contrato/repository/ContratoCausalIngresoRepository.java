package co.com.activos.model.contrato.repository;

import co.com.activos.model.contrato.ContratoCausalIngreso;
import reactor.core.publisher.Mono;

public interface ContratoCausalIngresoRepository {
    Mono<ContratoCausalIngreso> guardar(ContratoCausalIngreso contrato);
    Mono<ContratoCausalIngreso> buscarPorId(Long id);
    Mono<ContratoCausalIngreso> actualizar(ContratoCausalIngreso contrato);
}
