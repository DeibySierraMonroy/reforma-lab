package co.com.activos.jpa.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "V_CAUSALES_EMPRESA", schema = "RHU")
public class EmpresaCausalesViewData {


    @Column(name = "id_causal")
    private Long idCausal;

    @Column(name = "descripcion_causal")
    private String descripcionCausal;

    @Column(name = "id_causal_detalle")
    private Long idCausalDetalle;

    @Column(name = "descripcion_detalle")
    private String descripcionCausalDetalle;

    @Column(name = "tdc_td")
    private String tipoDocumento;

    @Column(name = "emp_nd")
    private Long numeroDocumento;

    @Id
    @Column(name = "id_relacion")
    private Long idRelacionDetalle;
}
