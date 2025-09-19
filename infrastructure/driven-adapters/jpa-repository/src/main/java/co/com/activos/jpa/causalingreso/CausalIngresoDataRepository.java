package co.com.activos.jpa.causalingreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausalIngresoDataRepository extends JpaRepository<CausalIngresoEntity, Long> {
    boolean existsByDescCausalIngreso(String descCausalIngreso);
}
