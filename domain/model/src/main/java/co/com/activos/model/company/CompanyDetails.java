package co.com.activos.model.company;

import lombok.Builder;
import java.util.Date;

@Builder
public record CompanyDetails(
    String tdcTdTemporal,
    Long empNdTemporal,
    String empresaPrincipal,
    String estadoSolicitud,
    Date fechaCreacion
) {}
