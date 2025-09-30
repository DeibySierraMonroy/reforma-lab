package co.com.activos.jpa.solicitud.actualizar;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "MESA_SOLICITUD_PERSONAL", schema = "RHU")
@Entity
public class ActualizarSolicitudDetalleData {

    @Id
    @Column(name = "ID_MESA_PERSONAL")
    private String id;
    @Column(name = "CAUSA_ORIGINA")
    private Long causa;
    @Column(name = "DETALLE_ORIGINA")
    private Long detalleOrigina;
    @Column(name = "ESPECIFICA_CAUSA")
    private Long especifiqueCausa;
    @Column(name = "CARGO_REEMPLAZA")
    private Long cargo;
    @Column(name = "NOMBRE_REEMPLAZA")
    private String nombreReemplazar;
    @Column(name = "FECHA_FIN_REEMPLAZA")
    private Date fechaFinReemplazo;
    @Column(name = "FECHA_FIN_CASUSAL")
    private Date fechaFinCausal;
    @Column(name = "MESA_ESTADO")
    private String estado;
    @Column(name = "OBSERVACION_CAUSA")
    private String observacionCausa;
    @Column(name = "AUD_USER")
    private String auditUser;
    @Column(name = "AUD_DATE")
    private Date auditDate;

}
