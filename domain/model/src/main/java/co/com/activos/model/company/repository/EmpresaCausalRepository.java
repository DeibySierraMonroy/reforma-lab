package co.com.activos.model.company.repository;

import co.com.activos.model.company.EmpresaCausales;
import reactor.core.publisher.Mono;

public interface EmpresaCausalRepository {
    Mono<EmpresaCausales> guardar(EmpresaCausales empresaCausales);
    Mono<String> delete(Long idParametrizacion);
}
