package co.com.activos.jpa.company;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.company.Company;
import co.com.activos.model.company.repository.CompanyRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;



@Repository
public class CompanyRepositoryAdapter extends AdapterOperations<Company, CompanyData, Long, CompanyDataRepository> implements CompanyRepository {
    protected CompanyRepositoryAdapter(CompanyDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Company.CompanyBuilder.class).build());
    }

    @Override
    public Mono<Company> findByIdAndTypeDocument(Long numberDocument, String typeDocument) {
        return Mono.fromCallable(() -> repository.findByNumberDocumentAndTypeDocument(numberDocument, typeDocument))
                .flatMap(Mono::justOrEmpty)
                .map(this::toEntity);
    }


}

