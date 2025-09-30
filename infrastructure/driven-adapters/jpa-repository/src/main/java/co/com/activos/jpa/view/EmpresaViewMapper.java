package co.com.activos.jpa.view;


import co.com.activos.model.company.view.EmpresaCausalesView;


public class EmpresaViewMapper {

    public static EmpresaCausalesView toEntity(EmpresaCausalesViewData data) {
        return EmpresaCausalesView.builder()
                .idCausal(data.getIdCausal())
                .descripcionCausal(data.getDescripcionCausal())
                .idCausalDetalle(data.getIdCausalDetalle())
                .descripcionCausalDetalle(data.getDescripcionCausalDetalle())
                .numeroDocumento(data.getNumeroDocumento())
                .tipoDocumento(data.getTipoDocumento())
                .idRelacionDetalle(data.getIdRelacionDetalle())
                .idParametrizacion(data.getIdParametrizacion())
                .build();
    }
}
