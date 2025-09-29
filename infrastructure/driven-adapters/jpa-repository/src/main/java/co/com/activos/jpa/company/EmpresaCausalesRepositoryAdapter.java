package co.com.activos.jpa.company;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.company.EmpresaCausales;
import co.com.activos.model.company.repository.EmpresaCausalRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class EmpresaCausalesRepositoryAdapter extends AdapterOperations<EmpresaCausales, EmpresaCausalesData, Long, EmpresaCausalesDataRepository>
        implements EmpresaCausalRepository {

    private final EmpresaCausalesDataRepository causalIngresoRelDetalleDataRepository;

    public EmpresaCausalesRepositoryAdapter(EmpresaCausalesDataRepository repository,
                                            ObjectMapper mapper,
                                            EmpresaCausalesDataRepository causalIngresoRelDetalleDataRepository) {
        super(repository, mapper, d -> mapper.mapBuilder(d, EmpresaCausales.EmpresaCausalesBuilder.class).build());
        this.causalIngresoRelDetalleDataRepository = causalIngresoRelDetalleDataRepository;
    }


    @Override
    public Mono<EmpresaCausales> guardar(EmpresaCausales empresaCausales) {
        if (empresaCausales == null) {
            return Mono.error(new IllegalArgumentException("EmpresaCausales cannot be null"));
        }
        
        return Mono.just(empresaCausales)
                .map(EmpresaCausalesMapper::toData)
                .flatMap(data -> Mono.fromCallable(() -> repository.save(data)))
                .map(EmpresaCausalesMapper::toDomain)
                .onErrorMap(e -> new RuntimeException("Error saving EmpresaCausales: " + e.getMessage(), e));
    }
}
