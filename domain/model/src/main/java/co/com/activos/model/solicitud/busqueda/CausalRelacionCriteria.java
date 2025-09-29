package co.com.activos.model.solicitud.busqueda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CausalRelacionCriteria {
    @FilterBy("tipoDocumento")
    private String tipoDocumento;
    @FilterBy("numeroDocumento")
    private Long numeroDocumento;
    @FilterBy("idCausal")
    private Long causal;

    private int pagina;
    private int size;
}
