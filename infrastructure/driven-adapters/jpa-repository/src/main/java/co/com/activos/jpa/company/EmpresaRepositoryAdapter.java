package co.com.activos.jpa.company;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.company.Empresa;
import co.com.activos.model.company.repository.EmpresaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class EmpresaRepositoryAdapter extends AdapterOperations<Empresa, EmpresaData, String, EmpresaDataRepository> implements EmpresaRepository {
    
    public EmpresaRepositoryAdapter(EmpresaDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Empresa.EmpresaBuilder.class).build());
    }

    @Override
    public Mono<List<Empresa>> buscarEmpresas(String nombre, int pagina, int total) {
        return Mono.fromCallable(() -> 
                repository.buscarEmpresas(
                    nombre,
                    PageRequest.of(pagina, total)
                ))
                .flatMap(page -> Flux.fromIterable(page.getContent())
                        .map(this::toEntity)
                        .collectList());
    }

    @Override
    public Mono<List<Empresa>> listaPorPaginado(String nombre, int pagina, int total) {
        return buscarEmpresas(nombre, pagina, total);
    }
}
