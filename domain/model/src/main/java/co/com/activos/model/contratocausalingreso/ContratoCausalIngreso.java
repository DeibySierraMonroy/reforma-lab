package co.com.activos.model.contratocausalingreso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class ContratoCausalIngreso {
    private Long idContratoCausalIngreso;
    private String tdcTd;
    private Long empNd;
    private Long ctoNumero;
    private String tdcTdFil;
    private Long empNdFil;
    private String tdcTdEpl;
    private Long eplNd;
    private Long idRelCausalIngresoDetalle;
    private String descLabor;
    private String codigoCargo;
    private String nombreCargo;
    private String nomEmpleadoReemplazar;
    private String descCausaIncremento;
    private Long idCalendarioDetalle;
    private Long idCausalIngreso;
    private String estado;
    private String estadoProceso;
    private String audUsuario;
    private LocalDateTime audFecha;
    private LocalDate fechaFin;
}
