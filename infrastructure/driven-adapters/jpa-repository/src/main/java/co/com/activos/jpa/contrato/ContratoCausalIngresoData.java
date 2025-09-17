package co.com.activos.jpa.contrato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTRATO_CAUSALES_INGRESO", schema = "RHU")
public class ContratoCausalIngresoData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTRATO_CAUSAL_INGRESO")
    @SequenceGenerator(name = "SEQ_CONTRATO_CAUSAL_INGRESO", sequenceName = "RHU.SQ_ID_CONTRATO_CAUSAL_INGRESO", allocationSize = 1)
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

    @Column(name = "NOM_EMPLEADO_REEMPLAZAR", length = 255)
    private String nomEmpleadoReemplazar;

    @Column(name = "DESC_CAUSA_INCREMENTO", length = 255)
    private String descCausaIncremento;

    @Column(name = "ID_CALENDARIO_DETALLE")
    private Long idCalendarioDetalle;

    @Column(name = "ESTADO", length = 1, columnDefinition = "VARCHAR2(1) DEFAULT 'A'")
    private String estado;

    @Column(name = "ESTADO_PROCESO", length = 30, columnDefinition = "VARCHAR2(30) DEFAULT 'EN_PROCESO'")
    private String estadoProceso;

    @Column(name = "AUD_USUARIO", length = 50, columnDefinition = "VARCHAR2(50) DEFAULT USER")
    private String audUsuario;

    @CreationTimestamp
    @Column(name = "AUD_FECHA", columnDefinition = "DATE DEFAULT SYSDATE")
    private LocalDateTime audFecha;

}
