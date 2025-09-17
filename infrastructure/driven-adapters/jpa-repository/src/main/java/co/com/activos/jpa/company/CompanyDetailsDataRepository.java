package co.com.activos.jpa.company;

import co.com.activos.model.company.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDetailsDataRepository extends JpaRepository<CompanyDetailsData, String> {
    
    @Query("SELECT new co.com.activos.model.company.CompanyDetails(" +
           "s.typeDocumentUsuaria, s.numberDocumentUsuaria, s.name, s.estadoSolicitud, s.fechaCreacion) " +
           "FROM CompanyDetailsData s " +
           "WHERE s.idSolicitud IN (" +
           "  SELECT MIN(s2.idSolicitud) " +
           "  FROM CompanyDetailsData s2 " +
           "  WHERE s2.typeDocument = :typeDocument AND s2.numberDocument = :numberDocument " +
           "  GROUP BY s2.typeDocumentUsuaria, s2.numberDocumentUsuaria" +
           ")")
    List<CompanyDetails> findByTypeDocumentAndNumberDocument(
        @Param("typeDocument") String typeDocument, 
        @Param("numberDocument") Long numberDocument
    );
}
