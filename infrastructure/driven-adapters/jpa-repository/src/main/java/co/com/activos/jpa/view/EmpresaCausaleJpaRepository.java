package co.com.activos.jpa.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EmpresaCausaleJpaRepository extends JpaRepository<EmpresaCausalesViewData, Long> , JpaSpecificationExecutor<EmpresaCausalesViewData> {
    Page<EmpresaCausalesViewData> findByNumeroDocumentoAndTipoDocumento(Long numberDocument, String typeDocument , Pageable pageable);
    EmpresaCausalesViewData findByNumeroDocumentoAndTipoDocumentoAndIdRelacionDetalle(Long numberDocument, String typeDocument,Long idCausalDetalle);
}
