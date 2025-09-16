package co.com.activos.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class CausalIngresoDetalleRequest {
    // Optional: if provided, must be > 0. Otherwise DB sequence will generate it.
    @Positive(message = "idCausalIngresoDet must be > 0")
    private Long idCausalIngresoDet;

    @NotBlank(message = "descCausalIngresoDet is required")
    @Size(max = 250, message = "descCausalIngresoDet length must be <= 250")
    private String descCausalIngresoDet;

    @Size(max = 3, message = "estado length must be <= 3")
    @Pattern(regexp = "A|I", message = "estado must be 'A' or 'I'")
    private String estado;

    @Size(max = 50, message = "audUsuario length must be <= 50")
    private String audUsuario;

    // Optional - if provided will be used as is
    private Date audFecha;
}
