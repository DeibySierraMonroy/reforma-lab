package co.com.activos.jpa.solicitud;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "MESA_SOLICITUD_PERSONAL", schema = "RHU")
@Entity
public class SolicitudPersonalData {

    @Id
    @Column(name = "ID_MESA_PERSONAL")
    private String idMesaPersonal;

    // FK to MESA_SOLICITUD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MESA", referencedColumnName = "ID_MESA", nullable = false)
    private SolicitudData solicitud;

    // Datos formulario
    @Column(name = "PRIMER_APELLIDO", nullable = false)
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;

    @Column(name = "PRIMER_NOMBRE", nullable = false)
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private String tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO", nullable = false)
    private String numeroDocumento;

    @Column(name = "TELEFONO_CONTACTO", nullable = false)
    private String telefonoContacto;

    @Column(name = "CARGO", nullable = false)
    private String cargo;

    @Column(name = "CAUSA_ORIGINA", nullable = false)
    private String causaOrigina; // 1,2,3 como CHAR(1)

    @Column(name = "DETALLE_ORIGINA")
    private String detalleOrigina; // CHAR(2)

    @Column(name = "ESPECIFICA_CAUSA")
    private String especificaCausa;

    @Column(name = "CARGO_REEMPLAZA")
    private String cargoReemplaza;

    @Column(name = "NOMBRE_REEMPLAZA")
    private String nombreReemplaza;

    @Column(name = "FECHA_FIN_REEMPLAZA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinReemplaza;

    @Column(name = "FECHA_FIN_CASUSAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinCausal;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "CIUDAD_TRABAJO", nullable = false)
    private String ciudadTrabajo;

    @Column(name = "SUCURSAL", nullable = false)
    private String sucursal;

    @Column(name = "CENTRO_COSTO", nullable = false)
    private String centroCosto;

    @Column(name = "PUNTO_VENTA")
    private String puntoVenta;

    @Column(name = "MODALIDAD_TRABAJO", nullable = false)
    private String modalidadTrabajo;

    @Column(name = "FECHA_INGRESO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Column(name = "NIVEL_RIESGO", nullable = false)
    private String nivelRiesgo;

    @Column(name = "SALARIO", nullable = false)
    private BigDecimal salario;

    @Column(name = "MODALIDAD_SALARIO", nullable = false)
    private String modalidadSalario;

    @Column(name = "HORAS_AL_MES")
    private String horasAlMes;

    @Column(name = "VALOR_HORA")
    private BigDecimal valorHora;

    @Column(name = "VALOR_DIA")
    private BigDecimal valorDia;

    @Column(name = "TRABAJA_SABADOS")
    private String trabajaSabados; // CHAR(1)

    @Column(name = "DIA_DESCANSO")
    private String diaDescanso;

    @Column(name = "PAGO_SUBSIDIO_TRANSPORTE")
    private String pagoSubsidioTransporte; // CHAR(1)

    @Column(name = "TIPO_AUXILIO")
    private String tipoAuxilio; // CHAR(1)

    @Column(name = "OTRO_AUXILIO")
    private String otroAuxilio; // CHAR(100)

    @Column(name = "VALOR_AUXILIO")
    private BigDecimal valorAuxilio;

    @Column(name = "COMISION")
    private String comision; // CHAR(1)

    @Column(name = "TIPO_COMISION")
    private String tipoComision;

    @Column(name = "PAGO_GARANTIZADO")
    private String pagoGarantizado; // CHAR(1)

    @Column(name = "MESES_PAGO_GARANTIZADO")
    private Integer mesesPagoGarantizado; // NUMBER(2)

    @Column(name = "OBSERVACION_PAGOS")
    private String observacionPagos; // VARCHAR2(200)

    @Column(name = "HORA_PRESENTACION", nullable = false)
    private String horaPresentacion;

    @Column(name = "LUGAR_PRESENTACION", nullable = false)
    private String lugarPresentacion;

    @Column(name = "TELEFONO_PRESENTACION")
    private String telefonoPresentacion;

    @Column(name = "NOMBRE_PRESENTACION")
    private String nombrePresentacion;

    @Column(name = "CARGO_PRESENTACION")
    private String cargoPresentacion;

    @Column(name = "LINK_PRESENTACION")
    private String linkPresentacion;
}
