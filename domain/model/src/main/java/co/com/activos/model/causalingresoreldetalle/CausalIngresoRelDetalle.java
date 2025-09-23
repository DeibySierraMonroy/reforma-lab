package co.com.activos.model.causalingresoreldetalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CausalIngresoRelDetalle {
    private Long idRelCausalIngresoDetalle;
    private Long idCausalIngreso;
    private Long idCausalIngresoDet;
    private String estado;
    private String audUsuario;
    private LocalDateTime audFecha;
    private String causalIngreso;
    private String causalIngresoDet;
}
