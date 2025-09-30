package co.com.activos.model.solicitud.actualizar;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ActualizarSolicitudDetalle {
    private String id;
    private Long causa;
    private Long detalleOrigina;
    private Long especifiqueCausa;
    private Long cargo;
    private String nombreReemplazar;
    private Date fechaFinReemplazo;
    private Date fechaFinCausal;
    private String estado;
    private String observacionCausa;
    private String auditUser;
    private Date auditDate;
}
