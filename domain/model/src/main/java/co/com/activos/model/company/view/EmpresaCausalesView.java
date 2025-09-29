package co.com.activos.model.company.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmpresaCausalesView {
    private Long idCausal;
    private String descripcionCausal;
    private Long idCausalDetalle;
    private String descripcionCausalDetalle;
    private Long numeroDocumento;
    private String tipoDocumento;
    private Long idRelacionDetalle;
}
