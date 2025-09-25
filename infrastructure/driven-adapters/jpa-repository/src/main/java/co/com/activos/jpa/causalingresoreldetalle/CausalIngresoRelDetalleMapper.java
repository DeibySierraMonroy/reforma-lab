package co.com.activos.jpa.causalingresoreldetalle;

import co.com.activos.model.causalingresoreldetalle.CausalIngresoRelDetalle;

public class CausalIngresoRelDetalleMapper {

    public static CausalIngresoRelDetalle toDomain(CausalIngresoRelDetalleData data) {
        if (data == null) {
            return null;
        }
        return CausalIngresoRelDetalle.builder()
                .idRelCausalIngresoDetalle(data.getIdRelCausalIngresoDetalle())
                .idCausalIngreso(data.getIdCausalIngreso())
                .idCausalIngresoDet(data.getIdCausalIngresoDet())
                .estado(data.getEstado())
                .audUsuario(data.getAudUsuario())
                .audFecha(data.getAudFecha())
                //.causalIngreso(data.getCausalIngreso())
                //.causalIngresoDet(data.getCausalIngresoDet())
                .build();
    }

    public static CausalIngresoRelDetalleData toData(CausalIngresoRelDetalle domain) {
        if (domain == null) {
            return null;
        }
        return CausalIngresoRelDetalleData.builder()
                .idRelCausalIngresoDetalle(domain.getIdRelCausalIngresoDetalle())
                .idCausalIngreso(domain.getIdCausalIngreso())
                .idCausalIngresoDet(domain.getIdCausalIngresoDet())
                .estado(domain.getEstado())
                .audUsuario(domain.getAudUsuario())
                .audFecha(domain.getAudFecha())
                //.causalIngreso(domain.getCausalIngreso())
                //.causalIngresoDet(domain.getCausalIngresoDet())
                .build();
    }
}
