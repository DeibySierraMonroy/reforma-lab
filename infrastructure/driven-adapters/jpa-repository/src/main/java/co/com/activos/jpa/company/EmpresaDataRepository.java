package co.com.activos.jpa.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDataRepository extends JpaRepository<EmpresaData, String> {
    @Query("SELECT e FROM EmpresaData e WHERE " +
           "(:nombre IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :nombre, '%')))")
    Page<EmpresaData> buscarEmpresas(
        @Param("nombre") String nombre, 
        Pageable pageable
    );
}
