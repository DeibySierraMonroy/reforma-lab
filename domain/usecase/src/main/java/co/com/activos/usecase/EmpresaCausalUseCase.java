package co.com.activos.usecase;

import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import co.com.activos.model.company.EmpresaCausales;
import co.com.activos.model.company.repository.EmpresaCausalRepository;
import co.com.activos.model.company.repository.EmpresaCausalesViewRepository;
import co.com.activos.model.company.view.EmpresaCausalesView;
import co.com.activos.model.solicitud.busqueda.CausalRelacionCriteria;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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

    public Flux<EmpresaCausalesView> buscarParametrizacion(CausalRelacionCriteria causalRelacionCriteria) {
        return empresaViewRepositoryAdapter.buscar(causalRelacionCriteria);
    }

}
