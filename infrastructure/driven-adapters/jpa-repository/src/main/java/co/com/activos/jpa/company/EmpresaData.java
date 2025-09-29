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
@Table(name = "EMPRESA")
@Entity
public class EmpresaData {
    @Id
    @Column(name = "EMP_ND")
    private Long numberDocument;
    @Column(name = "TDC_TD")
    private String typeDocument;
    @Column(name = "EMP_NOMBRE")
    private String name;
}
