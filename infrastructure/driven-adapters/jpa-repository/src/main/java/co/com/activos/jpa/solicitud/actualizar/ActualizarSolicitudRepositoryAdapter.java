package co.com.activos.jpa.solicitud.actualizar;

import co.com.activos.model.solicitud.Solicitud;
import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudDetalle;
import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ActualizarSolicitudRepositoryAdapter implements ActualizarSolicitudRepository {

    private final ActualizarSolicitudDataRepository repository;


    @Override
    public Mono<ActualizarSolicitudDetalle> actualizar(ActualizarSolicitudDetalle solicitud) {
        return Mono.fromCallable(() -> repository.findById(solicitud.getId())
                        .orElseThrow(() -> new RuntimeException("No se encontró la solicitud con id: " + solicitud.getId())))
                .map(existingData -> {
                    updateDataFromSolicitud(existingData, solicitud);
                    return repository.save(existingData);
                })
                .map(this::toDomain)
                .onErrorResume(e -> {
                    log.error("Error actualizando la solicitud con id: " + solicitud.getId(), e);
                    return Mono.error(new RuntimeException("Error al actualizar la solicitud", e));
                })
                .subscribeOn(Schedulers.boundedElastic());
    }


    private void updateDataFromSolicitud(ActualizarSolicitudDetalleData data, ActualizarSolicitudDetalle solicitud) {
        if (solicitud.getCausa() != null) data.setCausa(solicitud.getCausa());
        if (solicitud.getDetalleOrigina() != null) data.setDetalleOrigina(solicitud.getDetalleOrigina());
        if (solicitud.getEspecifiqueCausa() != null) data.setEspecifiqueCausa(solicitud.getEspecifiqueCausa());
        if (solicitud.getCargo() != null) data.setCargo(solicitud.getCargo());
        if (solicitud.getNombreReemplazar() != null) data.setNombreReemplazar(solicitud.getNombreReemplazar());
        if (solicitud.getFechaFinReemplazo() != null) data.setFechaFinReemplazo(solicitud.getFechaFinReemplazo());
        if (solicitud.getFechaFinCausal() != null) data.setFechaFinCausal(solicitud.getFechaFinCausal());
        if (solicitud.getEstado() != null) data.setEstado(solicitud.getEstado());
        if (solicitud.getObservacionCausa() != null) data.setObservacionCausa(solicitud.getObservacionCausa());
        if (solicitud.getAuditUser() != null) data.setAuditUser(solicitud.getAuditUser());
        if (solicitud.getAuditDate() != null) data.setAuditDate(solicitud.getAuditDate());
    }


    public ActualizarSolicitudDetalle toDomain(ActualizarSolicitudDetalleData data) {
        return ActualizarSolicitudDetalle.builder()
                .id(data.getId())
                .causa(data.getCausa())
                .detalleOrigina(data.getDetalleOrigina())
                .especifiqueCausa(data.getEspecifiqueCausa())
                .cargo(data.getCargo())
                .fechaFinReemplazo(data.getFechaFinReemplazo())
                .fechaFinCausal(data.getFechaFinCausal())
                .observacionCausa(data.getObservacionCausa())
                .nombreReemplazar(data.getNombreReemplazar())
                .estado(data.getEstado())
                .auditUser(data.getAuditUser())
                .auditDate(data.getAuditDate())
                .build();
    }

    public ActualizarSolicitudDetalleData toData(ActualizarSolicitudDetalle solicitud) {
        ActualizarSolicitudDetalleData data = new ActualizarSolicitudDetalleData();
        data.setId(solicitud.getId());
        data.setCausa(solicitud.getCausa());
        data.setDetalleOrigina(solicitud.getDetalleOrigina());
        data.setEspecifiqueCausa(solicitud.getEspecifiqueCausa());
        data.setCargo(solicitud.getCargo());
        data.setFechaFinReemplazo(solicitud.getFechaFinReemplazo());
        data.setFechaFinCausal(solicitud.getFechaFinCausal());
        data.setObservacionCausa(solicitud.getObservacionCausa());
        data.setNombreReemplazar(solicitud.getNombreReemplazar());
        data.setEstado(solicitud.getEstado());
        data.setAuditUser(solicitud.getAuditUser());
        data.setAuditDate(solicitud.getAuditDate());
        return data;
    }


}
