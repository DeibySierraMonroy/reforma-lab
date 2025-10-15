package co.com.activos.model.acceso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccesoIngreso {
    private String nombreConstante;
    private String valor;
    private String descripcion;
}
