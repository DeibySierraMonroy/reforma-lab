package co.com.activos.model.solicitud.busqueda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MesaSolicitudCriteria {

    @FilterBy("idMesa")
    private String idMesa;

    @FilterBy("tipoDocumento")
    private String tipoDoc;

    @FilterBy("numeroDocumento")
    private String numeroDoc;

    @FilterBy("empNdTemporal")
    private String empTemporal;

    @FilterBy("tdcTdTemporal")
    private String tdTemporal;

    private Integer tipoBusqueda;
}
