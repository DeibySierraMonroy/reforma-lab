package co.com.activos.usecase;

import co.com.activos.model.company.CompanyDetails;
import co.com.activos.model.company.repository.CompanyDetailsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CompanyDetailsUseCase {
    private final CompanyDetailsRepository companyDetailsRepository;

    public Flux<CompanyDetails> getCompanyDetails(String typeDocument, Long numberDocument) {
        return companyDetailsRepository.findByTypeDocumentAndNumberDocument(typeDocument, numberDocument);
    }
}
