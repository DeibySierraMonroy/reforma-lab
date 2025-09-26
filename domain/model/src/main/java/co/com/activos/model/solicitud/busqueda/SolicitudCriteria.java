package co.com.activos.model.solicitud.busqueda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SolicitudCriteria {

    @FilterBy("empNdUsuaria")
    private Long empTemporal;

    @FilterBy("tdcTdUsuaria")
    private String tdTemporal;

    @FilterBy("tdcTdTemporal")
    private String tdEmpresa;

    @FilterBy("empNdTemporal")
    private Long empEmpresa;
}
