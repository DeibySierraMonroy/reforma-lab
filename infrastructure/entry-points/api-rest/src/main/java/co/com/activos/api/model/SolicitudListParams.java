package co.com.activos.api.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class SolicitudListParams {
    @Min(value = 0, message = "page must be >= 0")
    private Integer page;

    @Min(value = 1, message = "size must be >= 1")
    private Integer size;

    @Size(max = 5, message = "estado length must be <= 5")
    private String estado;

    // Expected format yyyy-MM-dd (ISO)
    private String fechaInicio;
    private String fechaFin;

    @AssertTrue(message = "Both fechaInicio and fechaFin must be provided together")
    public boolean isBothDatesPresentOrNone() {
        return (fechaInicio == null && fechaFin == null) || (fechaInicio != null && fechaFin != null);
    }

    @AssertTrue(message = "Invalid date format. Expected yyyy-MM-dd")
    public boolean isDateFormatValid() {
        if (fechaInicio == null || fechaFin == null) return true;
        try {
            LocalDate.parse(fechaInicio);
            LocalDate.parse(fechaFin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @AssertTrue(message = "fechaInicio must be before or equal to fechaFin")
    public boolean isDateRangeValid() {
        if (fechaInicio == null || fechaFin == null) return true;
        try {
            LocalDate start = LocalDate.parse(fechaInicio);
            LocalDate end = LocalDate.parse(fechaFin);
            return !start.isAfter(end);
        } catch (Exception e) {
            return true; // handled by format validation
        }
    }

    public Date getFechaInicioSql() {
        if (fechaInicio == null) return null;
        return Date.valueOf(LocalDate.parse(fechaInicio));
    }

    public Date getFechaFinSql() {
        if (fechaFin == null) return null;
        return Date.valueOf(LocalDate.parse(fechaFin));
    }
}
