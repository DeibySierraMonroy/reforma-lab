package co.com.activos.jpa.causal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CausalIngresoDetalleDataRepository extends CrudRepository<CausalIngresoDetalleData, Long>, QueryByExampleExecutor<CausalIngresoDetalleData> {

    @Query(value = """
            SELECT 
                d.ID_CAUSAL_INGRESO_DET,
                d.DESC_CAUSAL_INGRESO_DET,
                CASE 
                    WHEN r.ID_CAUSAL_INGRESO_DET IS NOT NULL THEN 'RELACIONADA NO PARAMETRIZADA'
                    ELSE 'SIN RELACIÓN'
                END AS ESTADO,
                d.AUD_USUARIO,
                d.AUD_FECHA
            FROM RHU.CAUSALES_INGRESO_DETALLE d
            LEFT JOIN RHU.CAUSAL_INGRESO_REL_DETALLE r
                ON r.ID_CAUSAL_INGRESO_DET = d.ID_CAUSAL_INGRESO_DET
               AND (:idCausal IS NULL OR r.ID_CAUSAL_INGRESO = :idCausal)
            LEFT JOIN RHU.V_CAUSALES_EMPRESA vce
                ON vce.ID_CAUSAL_DETALLE = d.ID_CAUSAL_INGRESO_DET
               AND (:idCausal IS NULL OR vce.ID_CAUSAL = :idCausal)
               AND (:empresa IS NULL OR vce.EMP_ND = :empresa)
            WHERE vce.ID_CAUSAL_DETALLE IS NULL
            ORDER BY d.ID_CAUSAL_INGRESO_DET
            """, nativeQuery = true)
    List<CausalIngresoDetalleData> findNoParametrizadas(@Param("idCausal") Long idCausal, @Param("empresa") Long empresa);

    CausalIngresoDetalleData findByDescCausalIngresoDet(String descCausalIngresoDet);

}
