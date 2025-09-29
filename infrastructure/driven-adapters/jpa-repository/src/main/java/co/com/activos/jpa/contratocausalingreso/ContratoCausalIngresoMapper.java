package co.com.activos.jpa.contratocausalingreso;

import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;


public class ContratoCausalIngresoMapper {

    public static ContratoCausalIngreso toDomain(ContratoCausalIngresoData data) {
        if (data == null) {
            return null;
        }
        return ContratoCausalIngreso.builder()
                .idContratoCausalIngreso(data.getIdContratoCausalIngreso())
                .tdcTd(data.getTdcTd())
                .empNd(data.getEmpNd())
                .ctoNumero(data.getCtoNumero())
                .tdcTdFil(data.getTdcTdFil())
                .empNdFil(data.getEmpNdFil())
                .tdcTdEpl(data.getTdcTdEpl())
                .eplNd(data.getEplNd())
                .idRelCausalIngresoDetalle(data.getIdRelCausalIngresoDetalle())
                .descLabor(data.getDescLabor())
                .codigoCargo(data.getCodigoCargo())
                .nombreCargo(data.getNombreCargo())
                .nomEmpleadoReemplazar(data.getNomEmpleadoReemplazar())
                .descCausaIncremento(data.getDescCausaIncremento())
                .idCalendarioDetalle(data.getIdCalendarioDetalle())
                .estado(data.getEstado())
                .estadoProceso(data.getEstadoProceso())
                .audUsuario(data.getAudUsuario())
                .audFecha(data.getAudFecha())
                .fechaFin(data.getFechaFin())
                .idCausalIngreso(data.getIdCausalIngreso())
                .build();
    }

    public static ContratoCausalIngresoData toData(ContratoCausalIngreso domain) {
        if (domain == null) {
            return null;
        }
        return ContratoCausalIngresoData.builder()
                .idContratoCausalIngreso(domain.getIdContratoCausalIngreso())
                .tdcTd(domain.getTdcTd())
                .empNd(domain.getEmpNd())
                .ctoNumero(domain.getCtoNumero())
                .tdcTdFil(domain.getTdcTdFil())
                .empNdFil(domain.getEmpNdFil())
                .tdcTdEpl(domain.getTdcTdEpl())
                .eplNd(domain.getEplNd())
                .idRelCausalIngresoDetalle(domain.getIdRelCausalIngresoDetalle())
                .descLabor(domain.getDescLabor())
                .codigoCargo(domain.getCodigoCargo())
                .nombreCargo(domain.getNombreCargo())
                .nomEmpleadoReemplazar(domain.getNomEmpleadoReemplazar())
                .descCausaIncremento(domain.getDescCausaIncremento())
                .idCalendarioDetalle(domain.getIdCalendarioDetalle())
                .estado(domain.getEstado())
                .estadoProceso(domain.getEstadoProceso())
                .audUsuario(domain.getAudUsuario())
                .audFecha(domain.getAudFecha())
                .fechaFin(domain.getFechaFin())
                .idCausalIngreso(domain.getIdCausalIngreso())
                .build();
    }
}
