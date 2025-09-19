package co.com.activos.jpa.causalingresoreldetalle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAUSAL_INGRESO_REL_DETALLE", schema = "RHU")
public class CausalIngresoRelDetalleData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "causalIngresoRelDetalleSeq")
    @SequenceGenerator(name = "causalIngresoRelDetalleSeq", 
                      sequenceName = "RHU.SQ_ID_CAUSAL_INGRESO_REL_DET", 
                      allocationSize = 1)
    @Column(name = "ID_REL_CAUSAL_INGRESO_DETALLE")
    private Long idRelCausalIngresoDetalle;
    
    @Column(name = "ID_CAUSAL_INGRESO", nullable = false)
    private Long idCausalIngreso;
    
    @Column(name = "ID_CAUSAL_INGRESO_DET", nullable = false)
    private Long idCausalIngresoDet;
    
    @Column(name = "ESTADO", length = 3, columnDefinition = "VARCHAR2(3) DEFAULT 'A'")
    private String estado;
    
    @Column(name = "AUD_USUARIO", length = 50, columnDefinition = "VARCHAR2(50) DEFAULT USER")
    private String audUsuario;
    
    @CreationTimestamp
    @Column(name = "AUD_FECHA", columnDefinition = "DATE DEFAULT SYSDATE")
    private LocalDateTime audFecha;
}
