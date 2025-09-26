package co.com.activos.jpa.solicitud;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudPersonalDataRepository extends CrudRepository<SolicitudPersonalData, String> , JpaSpecificationExecutor<SolicitudData> {
    List<SolicitudPersonalData> findBySolicitud_IdSolicitud(String idSolicitud);
}
