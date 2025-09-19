package co.com.activos.jpa.causalingreso;

import co.com.activos.model.causalingreso.CausalIngreso;

public class CausalIngresoMapper {

    public static CausalIngreso toDomain(CausalIngresoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return CausalIngreso.builder()
                .idCausalIngreso(entity.getIdCausalIngreso())
                .idCausa(entity.getIdCausa())
                .descCausalIngreso(entity.getDescCausalIngreso())
                .estado(entity.getEstado())
                .audUsuario(entity.getAudUsuario())
                .audFecha(entity.getAudFecha())
                .build();
    }

    public static CausalIngresoEntity toData(CausalIngreso domain) {
        if (domain == null) {
            return null;
        }
        
        return CausalIngresoEntity.builder()
                .idCausalIngreso(domain.getIdCausalIngreso())
                .idCausa(domain.getIdCausa())
                .descCausalIngreso(domain.getDescCausalIngreso())
                .estado(domain.getEstado())
                .audUsuario(domain.getAudUsuario())
                .audFecha(domain.getAudFecha())
                .build();
    }
}
