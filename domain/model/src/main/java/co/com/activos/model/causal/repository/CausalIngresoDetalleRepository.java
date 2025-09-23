package co.com.activos.model.causal.repository;

import co.com.activos.model.causal.CausalIngresoDetalle;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CausalIngresoDetalleRepository {
    Mono<List<CausalIngresoDetalle>> listAll();
    Mono<CausalIngresoDetalle> getById(Long id);
    Mono<CausalIngresoDetalle> upsert(CausalIngresoDetalle causal);
    Mono<Void> deleteById(Long id);
    Mono<List<CausalIngresoDetalle>> findNoParametrizadas(Long idCausalIngreso);
}
