package co.com.activos.jpa.solicitud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(name = "MESA_SOLICITUD" , schema = "RHU")
@Entity
public class SolicitudData {

    @Id
    @Column(name = "ID_MESA")
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

    // Relación: Una Solicitud tiene muchos registros de SolicitudPersonal
    @OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SolicitudPersonalData> personal;
}
