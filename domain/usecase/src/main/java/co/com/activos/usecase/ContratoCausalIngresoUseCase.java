package co.com.activos.usecase;

import co.com.activos.model.contrato.ContratoCausalIngreso;
import co.com.activos.model.contrato.repository.ContratoCausalIngresoRepository;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ContratoCausalIngresoUseCase {

    private final ContratoCausalIngresoRepository repository;

    public Mono<ContratoCausalIngreso> guardarContrato(ContratoCausalIngreso contrato) {
        return validarContrato(contrato)
            .flatMap(repository::guardar);
    }

    public Mono<ContratoCausalIngreso> buscarPorId(Long id) {
        return repository.buscarPorId(id)
            .onErrorResume(e -> Mono.error(new BusinessException(
                ErrorCode.NOT_FOUND,
                String.format("No se encontró el contrato con id: %s", id)
            )));
    }

    public Mono<ContratoCausalIngreso> actualizarContrato(ContratoCausalIngreso contrato) {
        return validarContrato(contrato)
            .flatMap(repository::actualizar);
    }

    private Mono<ContratoCausalIngreso> validarContrato(ContratoCausalIngreso contrato) {
        return Mono.fromCallable(() -> {
            // Validar campos obligatorios
            if (contrato.tdcTd() == null || contrato.tdcTd().isBlank()) {
                throw new BusinessException(ErrorCode.BAD_REQUEST, "El tipo de documento es obligatorio");
            }
            if (contrato.empNd() == null) {
                throw new BusinessException(ErrorCode.BAD_REQUEST, "El número de documento es obligatorio");
            }
            // Agregar más validaciones según sea necesario
            return contrato;
        });
    }
}
