package co.com.activos.jpa.contratocausalingreso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTRATO_CAUSALES_INGRESO", schema = "RHU")
public class ContratoCausalIngresoData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contratoCausalIngresoSeq")
    @SequenceGenerator(name = "contratoCausalIngresoSeq", sequenceName = "RHU.SQ_ID_CONTRATO_CAUSAL_INGRESO", allocationSize = 1)
    @Column(name = "ID_CONTRATO_CAUSAL_INGRESO")
    private Long idContratoCausalIngreso;
    
    @Column(name = "TDC_TD", nullable = false, length = 3)
    private String tdcTd;
    
    @Column(name = "EMP_ND", nullable = false)
    private Long empNd;
    
    @Column(name = "CTO_NUMERO")
    private Long ctoNumero;
    
    @Column(name = "TDC_TD_FIL", nullable = false, length = 3)
    private String tdcTdFil;
    
    @Column(name = "EMP_ND_FIL", nullable = false)
    private Long empNdFil;
    
    @Column(name = "TDC_TD_EPL", nullable = false, length = 3)
    private String tdcTdEpl;
    
    @Column(name = "EPL_ND", nullable = false)
    private Long eplNd;
    
    @Column(name = "ID_REL_CAUSAL_INGRESO_DETALLE", nullable = false)
    private Long idRelCausalIngresoDetalle;
    
    @Column(name = "DESC_LABOR", length = 250)
    private String descLabor;
    
    @Column(name = "CODIGO_CARGO", length = 8)
    private String codigoCargo;
    
    @Column(name = "NOMBRE_CARGO", length = 250)
    private String nombreCargo;

    @Column(name = "ID_CAUSAL_INGRESO")
    private Long idCausalIngreso;
    
    @Column(name = "NOM_EMPLEADO_REEMPLAZAR", length = 255)
    private String nomEmpleadoReemplazar;
    
    @Column(name = "DESC_CAUSA_INCREMENTO", length = 255)
    private String descCausaIncremento;
    
    @Column(name = "ID_CALENDARIO_DETALLE")
    private Long idCalendarioDetalle;
    
    @Column(name = "ESTADO", length = 3, columnDefinition = "VARCHAR2(3) DEFAULT 'A'")
    private String estado;
    
    @Column(name = "ESTADO_PROCESO", length = 30, columnDefinition = "VARCHAR2(30) DEFAULT 'EN_PROCESO'")
    private String estadoProceso;
    
    @Column(name = "AUD_USUARIO", length = 50, updatable = false, columnDefinition = "VARCHAR2(50) DEFAULT USER")
    private String audUsuario;
    
    @CreationTimestamp
    @Column(name = "AUD_FECHA", updatable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    private Date audFecha;
    
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
}
