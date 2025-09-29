package co.com.activos.jpa.company;

import co.com.activos.model.company.EmpresaCausales;

public class EmpresaCausalesMapper {
    
    public static EmpresaCausales toDomain(EmpresaCausalesData data) {
        if (data == null) {
            return null;
        }
        
        return EmpresaCausales.builder()
                .idEmpresaCausal(data.getIdEmpresaCausal())
                .tipoDocumento(data.getTipoDocumento())
                .numeroDocumento(data.getNumeroDocumento())
                .idCausalIngresoRelDetalle(data.getCausalIngresoRelDetalle())
                .estado(data.getEstado())
                .audUsuario(data.getAudUsuario())
                .audFecha(data.getAudFecha())
                .build();
    }
    
    public static EmpresaCausalesData toData(EmpresaCausales domain) {
        if (domain == null) {
            return null;
        }
        
        return EmpresaCausalesData.builder()
                .idEmpresaCausal(domain.getIdEmpresaCausal())
                .tipoDocumento(domain.getTipoDocumento())
                .numeroDocumento(domain.getNumeroDocumento())
                .causalIngresoRelDetalle(domain.getIdCausalIngresoRelDetalle())
                .estado(domain.getEstado())
                .audUsuario(domain.getAudUsuario())
                .audFecha(domain.getAudFecha())
                .build();
    }

}
