package co.com.activos.jpa.causalingreso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAUSALES_INGRESO")
@EntityListeners(AuditingEntityListener.class)
public class CausalIngresoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "causal_ingreso_seq")
    @SequenceGenerator(name = "causal_ingreso_seq", sequenceName = "SQ_ID_CAUSAL_INGRESO", allocationSize = 1)
    @Column(name = "ID_CAUSAL_INGRESO", nullable = false, precision = 12)
    private Long idCausalIngreso;
    
    @Column(name = "ID_CAUSA", nullable = false, precision = 12)
    private Long idCausa;
    
    @Column(name = "DESC_CAUSAL_INGRESO", nullable = false, length = 255)
    private String descCausalIngreso;
    
    @Column(name = "ESTADO", length = 3, columnDefinition = "VARCHAR2(3) DEFAULT 'A'")
    private String estado;
    
    @CreatedBy
    @Column(name = "AUD_USUARIO", nullable = false, length = 50, columnDefinition = "VARCHAR2(50) DEFAULT USER")
    private String audUsuario;
    
    @CreatedDate
    @Column(name = "AUD_FECHA", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    private LocalDateTime audFecha;
}
