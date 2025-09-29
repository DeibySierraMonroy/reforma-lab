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
    private Long numeroDocumento;

    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;

    @Column(name = "CARGO")
    private String cargo;

    @Column(name = "CAUSA_ORIGINA")
    private String causaOrigina;

    @Column(name = "DES_DETALLE_ORIGINA")
    private String detalleOrigina;

    @Column(name = "DETALLE_ORIGINA")
    private Long idDetalleOrigina;

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

    @Column(name = "EMPRESA_USUARIA")
    private String nombreTemporal;

    @Column(name = "EMPRESA_PRINCIPAL")
    private String nombreUsuaria;

    @Column(name = "ID_PARMETRIZACION")
    private Long idParametrizacion;

    @Column(name = "OBSERVACION_CAUSA")
    private String observacionCausa;

    @Column(name = "CIUDAD_TRABAJO")
    private String ciudadTrabajo;

    @Column(name = "SUCURSAL")
    private String sucursal;

    @Column(name = "CENTRO_COSTO")
    private String centroCosto;

    @Column(name = "PUNTO_VENTA")
    private String puntoVenta;

    @Column(name = "MODALIDAD_TRABAJO")
    private String modalidadTrabajo;

    @Column(name = "FECHA_INGRESO")
    private Date fechaIngreso;

    @Column(name = "NIVEL_RIESGO")
    private String nivelRiesgo;

    @Column(name = "SALARIO")
    private Double salario;

    @Column(name = "MODALIDAD_SALARIO")
    private String modalidadSalario;

    @Column(name = "HORAS_AL_MES")
    private Integer horasAlMes;

    @Column(name = "VALOR_HORA")
    private Double valorHora;

    @Column(name = "VALOR_DIA")
    private Double valorDia;

    @Column(name = "TRABAJA_SABADOS")
    private String trabajaSabados;

    @Column(name = "DIA_DESCANSO")
    private String diaDescanso;

    @Column(name = "PAGO_SUBSIDIO_TRANSPORTE")
    private String pagoSubsidioTransporte;

    @Column(name = "TIPO_AUXILIO")
    private String tipoAuxilio;

    @Column(name = "OTRO_AUXILIO")
    private String otroAuxilio;

    @Column(name = "VALOR_AUXILIO")
    private Double valorAuxilio;

    @Column(name = "COMISION")
    private String comision;

    @Column(name = "TIPO_COMISION")
    private String tipoComision;

    @Column(name = "PAGO_GARANTIZADO")
    private String pagoGarantizado;

    @Column(name = "MESES_PAGO_GARANTIZADO")
    private Integer mesesPagoGarantizado;

    @Column(name = "OBSERVACION_PAGOS")
    private String observacionPagos;

    @Column(name = "HORA_PRESENTACION")
    private String horaPresentacion;

    @Column(name = "LUGAR_PRESENTACION")
    private String lugarPresentacion;

    @Column(name = "TELEFONO_PRESENTACION")
    private String telefonoPresentacion;

    @Column(name = "NOMBRE_PRESENTACION")
    private String nombrePresentacion;

    @Column(name = "CARGO_PRESENTACION")
    private String cargoPresentacion;

    @Column(name = "LINK_PRESENTACION")
    private String linkPresentacion;

    @Column(name = "AUD_USER")
    private String audUser;

    @Column(name = "AUD_DATE")
    private Date audDate;

    @Column(name = "ESTADO")
    private String estado;
}
