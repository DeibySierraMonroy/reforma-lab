package co.com.activos.jpa.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "V_MESA_SOLICITUD")
@Entity
public class CompanyDetailsData {
    @Id
    @Column(name = "ID_MESA")
    private String idSolicitud;
    
    @Column(name = "TDC_TD_TEMPORAL")
    private String typeDocument;
    
    @Column(name = "EMP_ND_TEMPORAL")
    private Long numberDocument;
    
    @Column(name = "TDC_TD_USUARIA")
    private String typeDocumentUsuaria;
    
    @Column(name = "EMP_ND_USUARIA")
    private Long numberDocumentUsuaria;
    
    @Column(name = "EMPRESA_USUARIA")
    private String name;
    
    @Column(name = "ESTADO_SOLICITUD")
    private String estadoSolicitud;
    
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
}
