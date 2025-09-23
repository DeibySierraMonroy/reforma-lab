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
    private Long numeroDoc;

    @FilterBy("empNdTemporal")
    private Long empTemporal;

    @FilterBy("tdcTdTemporal")
    private String tdTemporal;

    @FilterBy("idMesaPersonal")
    private String idMesaPersonal;
}
