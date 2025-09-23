package co.com.activos.jpa.causal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CausalIngresoDetalleDataRepository extends CrudRepository<CausalIngresoDetalleData, Long>, QueryByExampleExecutor<CausalIngresoDetalleData> {

    @Query(value = """
        SELECT d.*
        FROM RHU.CAUSALES_INGRESO_DETALLE d
        WHERE NOT EXISTS (
            SELECT 1
            FROM RHU.CAUSAL_INGRESO_REL_DETALLE r
            WHERE r.ID_CAUSAL_INGRESO = :idCausalIngreso
              AND r.ID_CAUSAL_INGRESO_DET = d.ID_CAUSAL_INGRESO_DET
        )
        """, nativeQuery = true)
    List<CausalIngresoDetalleData> findNoParametrizadas(@Param("idCausalIngreso") Long idCausalIngreso);

}
