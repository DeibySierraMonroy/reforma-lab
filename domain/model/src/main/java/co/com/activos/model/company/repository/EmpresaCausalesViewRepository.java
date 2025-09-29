package co.com.activos.model.company.repository;

import co.com.activos.model.company.view.EmpresaCausalesView;
import co.com.activos.model.solicitud.busqueda.CausalRelacionCriteria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EmpresaCausalesViewRepository {
    Flux<EmpresaCausalesView> buscar(CausalRelacionCriteria causalRelacionCriteria);
    Mono<EmpresaCausalesView> buscarParametrizacion(Long numerDocument, String typeDocument, Long idCausalIngreso);
}
