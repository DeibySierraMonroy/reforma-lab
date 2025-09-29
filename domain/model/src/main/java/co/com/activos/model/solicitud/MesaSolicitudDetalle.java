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
    private Long idDetalleOrigina;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String tipoDocumento;
    private Long numeroDocumento;
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
    private Long idParametrizacion;
    private String observacionCausa;
    private String ciudadTrabajo;
    private String sucursal;
    private String centroCosto;
    private String puntoVenta;
    private String modalidadTrabajo;
    private Date fechaIngreso;
    private String nivelRiesgo;
    private Double salario;
    private String modalidadSalario;
    private Integer horasAlMes;
    private Double valorHora;
    private Double valorDia;
    private String trabajaSabados;
    private String diaDescanso;
    private String pagoSubsidioTransporte;
    private String tipoAuxilio;
    private String otroAuxilio;
    private Double valorAuxilio;
    private String comision;
    private String tipoComision;
    private String pagoGarantizado;
    private Integer mesesPagoGarantizado;
    private String observacionPagos;
    private String horaPresentacion;
    private String lugarPresentacion;
    private String telefonoPresentacion;
    private String nombrePresentacion;
    private String cargoPresentacion;
    private String linkPresentacion;
    private String audUser;
    private Date audDate;
    private String estado;
}
