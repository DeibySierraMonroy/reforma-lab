package co.com.activos.model.causal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CausalIngresoDetalleModel {
    private Long idCausalIngresoDet;
    private String descCausalIngresoDet;
    private String estado;
    private String audUsuario;
    private LocalDateTime audFecha;
}
