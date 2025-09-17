package co.com.activos.jpa.contrato.mapper;

import co.com.activos.jpa.contrato.ContratoCausalIngresoData;
import co.com.activos.model.contrato.ContratoCausalIngreso;

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
            .build();
    }

    public static ContratoCausalIngresoData toData(ContratoCausalIngreso domain) {
        if (domain == null) {
            return null;
        }
        
        return ContratoCausalIngresoData.builder()
            .idContratoCausalIngreso(domain.idContratoCausalIngreso())
            .tdcTd(domain.tdcTd())
            .empNd(domain.empNd())
            .ctoNumero(domain.ctoNumero())
            .tdcTdFil(domain.tdcTdFil())
            .empNdFil(domain.empNdFil())
            .tdcTdEpl(domain.tdcTdEpl())
            .eplNd(domain.eplNd())
            .idRelCausalIngresoDetalle(domain.idRelCausalIngresoDetalle())
            .descLabor(domain.descLabor())
            .codigoCargo(domain.codigoCargo())
            .nombreCargo(domain.nombreCargo())
            .nomEmpleadoReemplazar(domain.nomEmpleadoReemplazar())
            .descCausaIncremento(domain.descCausaIncremento())
            .idCalendarioDetalle(domain.idCalendarioDetalle())
            .estado(domain.estado())
            .estadoProceso(domain.estadoProceso())
            .audUsuario(domain.audUsuario())
            .audFecha(domain.audFecha())
            .build();
    }
}
