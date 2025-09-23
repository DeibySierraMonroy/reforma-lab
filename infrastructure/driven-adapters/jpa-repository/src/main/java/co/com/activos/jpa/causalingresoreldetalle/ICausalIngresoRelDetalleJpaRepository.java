package co.com.activos.jpa.causalingresoreldetalle;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ICausalIngresoRelDetalleJpaRepository extends JpaRepository<CausalIngresoRelDetalleData, Long> {

    List<CausalIngresoRelDetalleData> findByIdCausalIngreso(Long idCausalIngreso);
}
