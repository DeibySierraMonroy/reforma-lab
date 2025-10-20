package co.com.activos.jpa.contratocausalingreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoCausalIngresoDataRepository extends JpaRepository<ContratoCausalIngresoData, Long> {
    List<ContratoCausalIngresoData> findByEstado(String estado);
    ContratoCausalIngresoData findByTdcTdAndEmpNdAndTdcTdFilAndEmpNdFilAndTdcTdEplAndEplNd(String tdcTd, Long empNd,String tdcTdFil,Long empNdFil,String tdcTdEpl, Long eplNd );
}
