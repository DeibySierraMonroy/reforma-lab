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
        String causaOrigina,
        String detalleOrigina,
        String especificaCausa,
        String cargoReemplaza,
        String nombreReemplaza,
        Date fechaFinReemplaza,
        Date fechaFinCausal,
        String observaciones,
        String ciudadTrabajo,
        String sucursal,
        String centroCosto,
        String puntoVenta,
        String modalidadTrabajo,
        Date fechaIngreso,
        String nivelRiesgo,
        BigDecimal salario,
        String modalidadSalario,
        String horasAlMes,
        BigDecimal valorHora,
        BigDecimal valorDia,
        String trabajaSabados,
        String diaDescanso,
        String pagoSubsidioTransporte,
        String tipoAuxilio,
        String otroAuxilio,
        BigDecimal valorAuxilio,
        String comision,
        String tipoComision,
        String pagoGarantizado,
        Integer mesesPagoGarantizado,
        String observacionPagos,
        String horaPresentacion,
        String lugarPresentacion,
        String telefonoPresentacion,
        String nombrePresentacion,
        String cargoPresentacion,
        String linkPresentacion
) {}
