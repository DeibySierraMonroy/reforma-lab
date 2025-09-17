package co.com.activos.jpa.company;

import co.com.activos.model.company.CompanyDetails;
import co.com.activos.model.company.repository.CompanyDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyDetailsRepositoryAdapter implements CompanyDetailsRepository {

    private final CompanyDetailsDataRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<CompanyDetails> findByTypeDocumentAndNumberDocument(String typeDocument, Long numberDocument) {
        return Flux.defer(() -> {
            List<CompanyDetails> details = repository.findByTypeDocumentAndNumberDocument(typeDocument, numberDocument);
            return Flux.fromIterable(details);
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
