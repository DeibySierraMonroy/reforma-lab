package co.com.activos.jpa.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPRESA_CAUSALES", schema = "RHU")
public class EmpresaCausalesData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresaCausalesSeq")
    @SequenceGenerator(name = "empresaCausalesSeq", sequenceName = "rhu.SEQ_EMPRESA_CAUSALES", allocationSize = 1)
    @Column(name = "ID_EMPRESA_CAUSAL")
    private Long idEmpresaCausal;

    @Column(name = "TDC_TD", nullable = false, length = 3)
    private String tipoDocumento;

    @Column(name = "EMP_ND", nullable = false)
    private Long numeroDocumento;

    @Column(name = "ID_REL_CAUSAL_INGRESO_DETALLE", nullable = false)
    private Long causalIngresoRelDetalle;

    @Column(name = "ESTADO", length = 1)
    private String estado;

    @Column(name = "AUD_USUARIO", length = 50)
    private String audUsuario;

    @Column(name = "AUD_FECHA")
    private Date audFecha;

    @PrePersist
    public void prePersist() {
        if (this.estado == null) {
            this.estado = "A";
        }
        if (this.audUsuario == null) {
            this.audUsuario = "SYS";
        }
        this.audFecha = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.audFecha = new Date();
    }
}
