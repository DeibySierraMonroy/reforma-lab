package co.com.activos.jpa.solicitud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import java.util.Date;

public interface SolicitudDataRepository extends CrudRepository<SolicitudData, String>, QueryByExampleExecutor<SolicitudData> {
    Page<SolicitudData> findAllBy(Pageable pageable);
    Page<SolicitudData> findByEstadoSolicitud(String estadoSolicitud, Pageable pageable);
    Page<SolicitudData> findByFechaCreacionBetween(Date fechaInicio, Date fechaFin, Pageable pageable);
    Page<SolicitudData> findByEstadoSolicitudAndFechaCreacionBetween(String estadoSolicitud, Date fechaInicio, Date fechaFin, Pageable pageable);
}
