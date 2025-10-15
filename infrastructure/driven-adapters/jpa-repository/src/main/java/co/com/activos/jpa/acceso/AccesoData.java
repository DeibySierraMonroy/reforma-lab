package co.com.activos.jpa.acceso;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "DETCONST")
@Entity
public class AccesoData {

    @Id
    @Column(name = "CTE_NOMBRE")
    private String nombreConstante;
    @Column(name = "DTC_VALOR")
    private String valor;
    @Column(name = "DTC_DESCRP")
    private String descripcion;
}
