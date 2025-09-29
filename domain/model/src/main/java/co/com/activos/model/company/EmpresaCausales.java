package co.com.activos.model.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaCausales {
    private Long idEmpresaCausal;
    private String tipoDocumento;
    private Long numeroDocumento;
    private Long idCausalIngresoRelDetalle;
    private String estado;
    private String audUsuario;
    private Date audFecha;
}
