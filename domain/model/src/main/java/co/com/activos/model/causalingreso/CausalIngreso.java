package co.com.activos.model.causalingreso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CausalIngreso {
    private Long idCausalIngreso;
    private Long idCausa;
    private String descCausalIngreso;
    private String estado;
    private String audUsuario;
    private LocalDateTime audFecha;
}
