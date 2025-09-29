package co.com.activos.model.company.repository;

import co.com.activos.model.company.Empresa;
import reactor.core.publisher.Mono;
import java.util.List;

public interface EmpresaRepository {
    Mono<List<Empresa>> buscarEmpresas(String nombre, int pagina, int total);
    Mono<List<Empresa>> listaPorPaginado(String nombre, int pagina, int total);

}
