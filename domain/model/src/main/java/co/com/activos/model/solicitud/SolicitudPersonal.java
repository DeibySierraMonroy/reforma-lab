package co.com.activos.model.solicitud;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record SolicitudPersonal(
        String idMesaPersonal,
        String primerApellido,
        String segundoApellido,
        String primerNombre,
        String segundoNombre,
        String tipoDocumento,
        String numeroDocumento,
        String telefonoContacto,
        String cargo,
        String estadoPersona

) {}
