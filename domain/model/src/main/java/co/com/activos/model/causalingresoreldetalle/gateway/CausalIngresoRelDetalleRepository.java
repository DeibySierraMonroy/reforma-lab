package co.com.activos.model.causalingresoreldetalle.gateway;

import co.com.activos.model.causalingresoreldetalle.CausalIngresoRelDetalle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CausalIngresoRelDetalleRepository {
    Mono<CausalIngresoRelDetalle> findById(Long id);
    Flux<CausalIngresoRelDetalle> findAll();
    Mono<CausalIngresoRelDetalle> save(CausalIngresoRelDetalle causalIngresoRelDetalle);
    Mono<CausalIngresoRelDetalle> update(CausalIngresoRelDetalle causalIngresoRelDetalle);
    Mono<Void> deleteById(Long id);
    Mono<List<CausalIngresoRelDetalle>> findParametrizadas(Long idCausalIngreso);
    Mono<CausalIngresoRelDetalle> findByIdCausalIngresoAndIdCausalIngresoDet(Long idCausalIngreso, Long idCausalIngresoDet);
}
