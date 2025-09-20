package co.com.activos.jpa.mesa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "V_MESA_SOLICITUD_DETALLE", schema = "RHU")
public class MesaSolicitudDetalleData {
    @Id
    @Column(name = "ID_MESA_PERSONAL")
    private String idMesaPersonal;

    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;

    @Column(name = "CARGO")
    private String cargo;

    @Column(name = "CAUSA_ORIGINA")
    private String causaOrigina;

    @Column(name = "DETALLE_ORIGINA")
    private String detalleOrigina;

    @Column(name = "ESPECIFICA_CAUSA")
    private String especificaCausa;

    @Column(name = "CARGO_REEMPLAZA")
    private String cargoReemplaza;

    @Column(name = "NOMBRE_REEMPLAZA")
    private String nombreReemplaza;

    @Column(name = "FECHA_FIN_REEMPLAZA")
    private Date fechaFinReemplaza;

    @Column(name = "FECHA_FIN_CASUSAL")
    private Date fechaFinCausal;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "TDC_TD_TEMPORAL")
    private String tdcTdTemporal;

    @Column(name = "EMP_ND_TEMPORAL")
    private Long empNdTemporal;

    @Column(name = "TDC_TD_USUARIA")
    private String tdcTdUsuaria;

    @Column(name = "EMP_ND_USUARIA")
    private Long empNdUsuaria;

    @Column(name = "ID_MESA")
    private String idMesa;
}
