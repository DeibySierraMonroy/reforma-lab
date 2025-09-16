package co.com.activos.model.causal;

import lombok.Builder;

import java.util.Date;

@Builder
public record CausalIngresoDetalle(
        Long idCausalIngresoDet,
        String descCausalIngresoDet,
        String estado,
        String audUsuario,
        Date audFecha
) {}
