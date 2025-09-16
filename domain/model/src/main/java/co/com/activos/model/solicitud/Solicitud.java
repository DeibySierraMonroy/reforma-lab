package co.com.activos.model.solicitud;

import lombok.Builder;

import java.util.Date;

@Builder
public record Solicitud(
        String idSolicitud,
        String tdcTdTemporal,
        Long empNdTemporal,
        String tdcTdUsuaria,
        Long empNdUsuaria,
        String estadoSolicitud,
        Date fechaCreacion
) {}
