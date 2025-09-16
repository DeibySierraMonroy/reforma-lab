package co.com.activos.model.company.repository;

import co.com.activos.model.company.Company;
import reactor.core.publisher.Mono;

public interface CompanyRepository {
    Mono<Company> findByIdAndTypeDocument(Long numberDocument, String typeDocument);
}
