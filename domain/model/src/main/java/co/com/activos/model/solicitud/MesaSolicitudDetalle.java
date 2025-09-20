package co.com.activos.model.solicitud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesaSolicitudDetalle {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefonoContacto;
    private String cargo;
    private String causaOrigina;
    private String detalleOrigina;
    private String especificaCausa;
    private String cargoReemplaza;
    private String nombreReemplaza;
    private Date fechaFinReemplaza;
    private Date fechaFinCausal;
    private String observaciones;
    private String tdcTdTemporal;
    private Long empNdTemporal;
    private String tdcTdUsuaria;
    private Long empNdUsuaria;
    private String idMesa;
    private String idMesaPersonal;
    private String nombreTemporal;
    private String nombreUsuaria;
    private Date fecha;
    private String estado;
}
