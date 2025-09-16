package co.com.activos.jpa.causal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "CAUSALES_INGRESO_DETALLE", schema = "RHU")
@Entity
public class CausalIngresoDetalleData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAUSALES_INGRESO_DETALLE")
    @SequenceGenerator(name = "SEQ_CAUSALES_INGRESO_DETALLE", sequenceName = "RHU.SQ_CAUSALES_INGRESO_DETALLE", allocationSize = 1)
    @Column(name = "ID_CAUSAL_INGRESO_DET")
    private Long idCausalIngresoDet;

    @Column(name = "DESC_CAUSAL_INGRESO_DET")
    private String descCausalIngresoDet;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "AUD_USUARIO")
    private String audUsuario;

    @Column(name = "AUD_FECHA")
    private Date audFecha;
}
