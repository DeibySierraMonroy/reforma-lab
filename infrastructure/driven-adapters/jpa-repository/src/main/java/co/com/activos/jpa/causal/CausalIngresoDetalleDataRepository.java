package co.com.activos.jpa.causal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CausalIngresoDetalleDataRepository extends CrudRepository<CausalIngresoDetalleData, Long>, QueryByExampleExecutor<CausalIngresoDetalleData> {
}
