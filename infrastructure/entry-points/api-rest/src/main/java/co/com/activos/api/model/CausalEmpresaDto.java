package co.com.activos.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CausalEmpresaDto {
    private Long numeroDocumento;
    private String tipoDocumento;
    private String audUsuario;
    private Long idCausalIngreso;
    private Long idCausalIngresoDet;
}
