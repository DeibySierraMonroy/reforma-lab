package co.com.activos.jpa.solicitud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface SolicitudDataRepository extends CrudRepository<SolicitudData, String>, QueryByExampleExecutor<SolicitudData> {
    Page<SolicitudData> findAllBy(Pageable pageable);
    Page<SolicitudData> findByEstadoSolicitud(String estadoSolicitud, Pageable pageable);
    Page<SolicitudData> findByFechaCreacionBetween(Date fechaInicio, Date fechaFin, Pageable pageable);
    Page<SolicitudData> findByEstadoSolicitudAndFechaCreacionBetween(String estadoSolicitud, Date fechaInicio, Date fechaFin, Pageable pageable);

    @EntityGraph(attributePaths = "personal")
    Optional<SolicitudData> findByIdSolicitud(String idSolicitud);

    @Query("select distinct s from SolicitudData s left join fetch s.personal where s.idSolicitud = :id")
    Optional<SolicitudData> findWithPersonalById(@Param("id") String id);

    @EntityGraph(attributePaths = "personal")
    Optional<SolicitudData> findById(String id);
}
