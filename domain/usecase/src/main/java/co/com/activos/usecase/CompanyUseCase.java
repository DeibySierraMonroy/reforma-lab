package co.com.activos.usecase;

import co.com.activos.model.company.Company;
import co.com.activos.model.company.repository.CompanyRepository;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.List;

@RequiredArgsConstructor
public class CompanyUseCase {
    private final CompanyRepository companyRepository;


    public Mono<Company> searchCompany(Long numberDocument, String typeDocument) {
        return companyRepository.findByIdAndTypeDocument(numberDocument, typeDocument)
                .switchIfEmpty(Mono.error(new BusinessException(
                        ErrorCode.NOT_FOUND,
                        "Company not found with numberDocument=" + numberDocument + " and typeDocument=" + typeDocument
                )));
    }

    public Mono<List<Company>> findAll() {
        return Mono.fromCallable(companyRepository::findAll);
    }
}
