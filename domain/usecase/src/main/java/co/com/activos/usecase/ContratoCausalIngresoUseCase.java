package co.com.activos.usecase;

import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import co.com.activos.model.contratocausalingreso.ContratoCausalIngreso;
import co.com.activos.model.contratocausalingreso.gateway.ContratoCausalIngresoRepository;
import co.com.activos.model.solicitud.Estado;
import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudDetalle;
import co.com.activos.model.solicitud.actualizar.ActualizarSolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ContratoCausalIngresoUseCase {

    private final ContratoCausalIngresoRepository repository;
    private final ActualizarSolicitudRepository actualizarSolicitudRepository;

    public Flux<ContratoCausalIngreso> findAll() {
        return repository.findAll();
    }

    public Mono<ContratoCausalIngreso> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<ContratoCausalIngreso> save(ContratoCausalIngreso contrato) {

        return repository.validarExistencia(contrato)
                .switchIfEmpty(
                        repository.save(contrato)
                                .flatMap(saved -> actualizarSolicitudRepository
                                        .actualizar(actualizarSolcitud(saved, Estado.Aprobado.getName()))
                                        .thenReturn(saved)
                                )
                )
                .flatMap(contratoCausalIngreso ->
                        actualizarSolicitudRepository
                                .actualizar(actualizarSolcitud(contratoCausalIngreso, Estado.Inactivo.getName()))
                                .then(Mono.error(new BusinessException(
                                        ErrorCode.BAD_REQUEST,
                                        "No es posible aprobar el candidato debido a que ya tiene una solicitud vigente")
                                ))
                );

    }


    public Mono<ContratoCausalIngreso> update(ContratoCausalIngreso contrato) {
        return repository.update(contrato);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private ActualizarSolicitudDetalle actualizarSolcitud(ContratoCausalIngreso contrato,
                                                          String estado) {
        return ActualizarSolicitudDetalle
                .builder()
                .id(contrato.getIdPersonal())
                .estado(estado)
                .build();
    }

}

