package co.com.activos.jpa.company;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "V_EMPRESA_ACCESO" , schema = "GCP")
@Entity
public class EmpresaData {
    @Id
    @Column(name = "NIT_EMPRESA")
    private Long numberDocument;

    @Column(name = "EMPRESA")
    private String name;
}
