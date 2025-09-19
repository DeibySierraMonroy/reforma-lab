package co.com.activos.model.solicitud;

import lombok.Builder;

import java.util.List;

@Builder
public record SolicitudDetalle(
        Solicitud solicitud,
        List<SolicitudPersonal> personal
) {}
