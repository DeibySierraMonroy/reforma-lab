package co.com.activos.jpa.causalingreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface CausalIngresoDataRepository extends JpaRepository<CausalIngresoEntity, Long> {
    boolean existsByDescCausalIngreso(String descCausalIngreso);
    List<CausalIngresoEntity> findByEstado(String estado);
}
