package co.com.activos.jpa.solicitud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "MESA_SOLICITUD" , schema = "RHU")
@Entity
public class SolicitudData {

    @Id
    @Column(name = "ID_SOLICITUD")
    private String idSolicitud;

    @Column(name = "TDC_TD_TEMPORAL")
    private String tdcTdTemporal;

    @Column(name = "EMP_ND_TEMPORAL")
    private Long empNdTemporal;

    @Column(name = "TDC_TD_USUARIA")
    private String tdcTdUsuaria;

    @Column(name = "EMP_ND_USUARIA")
    private Long empNdUsuaria;

    @Column(name = "ESTADO_SOLICITUD")
    private String estadoSolicitud;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
}
