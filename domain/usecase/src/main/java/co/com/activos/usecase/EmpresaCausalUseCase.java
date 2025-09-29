package co.com.activos.usecase;

import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import co.com.activos.model.company.EmpresaCausales;
import co.com.activos.model.company.repository.EmpresaCausalRepository;
import co.com.activos.model.company.repository.EmpresaCausalesViewRepository;
import co.com.activos.model.company.view.EmpresaCausalesView;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class EmpresaCausalUseCase {

    private final EmpresaCausalRepository empresaCausalRepository;
    private final EmpresaCausalesViewRepository empresaViewRepositoryAdapter;

    public Mono<EmpresaCausales> guardar(EmpresaCausales empresaCausales) {
        return empresaViewRepositoryAdapter.buscarParametrizacion(empresaCausales.getNumeroDocumento(),
                        empresaCausales.getTipoDocumento(), empresaCausales.getIdCausalIngresoRelDetalle())
                .flatMap(existing -> Mono.<EmpresaCausales>error(new BusinessException(ErrorCode.BAD_REQUEST,
                        "Ya existe una parametrización con estas características")))
                .switchIfEmpty(Mono.defer(() -> empresaCausalRepository.guardar(empresaCausales)));

    }

    public Mono<List<EmpresaCausalesView>> findByNumberDocumentAndTypeDocument(Long numberDocument, String typeDocument, int pagina, int total) {
        return empresaViewRepositoryAdapter.findByNumberDocumentAndTypeDocument(numberDocument, typeDocument, pagina, total);
    }

}
