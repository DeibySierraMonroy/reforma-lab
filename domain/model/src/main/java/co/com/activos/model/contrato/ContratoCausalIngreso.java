package co.com.activos.model.contrato;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ContratoCausalIngreso(
    Long idContratoCausalIngreso,
    String tdcTd,
    Long empNd,
    Long ctoNumero,
    String tdcTdFil,
    Long empNdFil,
    String tdcTdEpl,
    Long eplNd,
    Long idRelCausalIngresoDetalle,
    String descLabor,
    String codigoCargo,
    String nombreCargo,
    String nomEmpleadoReemplazar,
    String descCausaIncremento,
    Long idCalendarioDetalle,
    String estado,
    String estadoProceso,
    String audUsuario,
    LocalDateTime audFecha
) {
    public static ContratoCausalIngresoBuilder builder() {
        return new ContratoCausalIngresoBuilder()
            .estado("A")
            .estadoProceso("EN_PROCESO");
    }
}
