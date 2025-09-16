package co.com.activos.jpa.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface CompanyDataRepository extends CrudRepository<CompanyData, Long> , QueryByExampleExecutor<CompanyData> {
    CompanyData findByNumberDocumentAndTypeDocument(Long numberDocument, String typeDocument);
}
