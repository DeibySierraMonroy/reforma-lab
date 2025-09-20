package co.com.activos.jpa.mesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMesaSolicitudDetalleJpaRepository extends JpaRepository<MesaSolicitudDetalleData, String> , JpaSpecificationExecutor<MesaSolicitudDetalleData> {
    List<MesaSolicitudDetalleData> findByIdMesaPersonal(String idMesaPersonal);
}
