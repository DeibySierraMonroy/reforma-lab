package co.com.activos.jpa.company;

import co.com.activos.model.company.Empresa;

public class EmpresaMapper {
    public static Empresa toDomain(EmpresaData data) {
        if (data == null) {
            return null;
        }
        
        return Empresa.builder()
                .numberDocument(data.getNumberDocument())
                .name(data.getName())
                .build();
    }
    
    public static EmpresaData toData(Empresa domain) {
        if (domain == null) {
            return null;
        }
        
        return EmpresaData.builder()
                .numberDocument(domain.getNumberDocument())
                .name(domain.getName())
                .build();
    }
}
