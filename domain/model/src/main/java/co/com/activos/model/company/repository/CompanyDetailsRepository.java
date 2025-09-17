package co.com.activos.model.company.repository;

import co.com.activos.model.company.CompanyDetails;
import reactor.core.publisher.Flux;

public interface CompanyDetailsRepository {
    Flux<CompanyDetails> findByTypeDocumentAndNumberDocument(String typeDocument, Long numberDocument);
}
