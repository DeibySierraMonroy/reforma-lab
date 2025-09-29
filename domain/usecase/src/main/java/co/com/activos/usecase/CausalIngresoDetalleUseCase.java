package co.com.activos.usecase;

import co.com.activos.model.causal.CausalIngresoDetalle;
import co.com.activos.model.causal.repository.CausalIngresoDetalleRepository;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CausalIngresoDetalleUseCase {
    private final CausalIngresoDetalleRepository repository;

    public Mono<List<CausalIngresoDetalle>> listAll() {
        return repository.listAll();
    }

    public Mono<CausalIngresoDetalle> getById(Long id) {
        return repository.getById(id)
                .switchIfEmpty(Mono.error(new BusinessException(
                        ErrorCode.NOT_FOUND,
                        "CausalIngresoDetalle not found with id=" + id
                )));
    }

    public Mono<CausalIngresoDetalle> create(CausalIngresoDetalle causal) {
        return repository.findByDescCausalIngresoDet(causal.descCausalIngresoDet())
                .flatMap(existing -> Mono.<CausalIngresoDetalle>error(new BusinessException(
                        ErrorCode.BAD_REQUEST,
                        "Ya existe una Causal detalle con la descripción: " + causal.descCausalIngresoDet())))
                .switchIfEmpty(Mono.defer(() -> repository.upsert(causal)));
    }

    public Mono<CausalIngresoDetalle> update(Long id, CausalIngresoDetalle causal) {
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "Path variable id must be > 0 for update");
        }
        CausalIngresoDetalle toSave = CausalIngresoDetalle.builder()
                .idCausalIngresoDet(id)
                .descCausalIngresoDet(causal.descCausalIngresoDet())
                .estado(causal.estado())
                .audUsuario(causal.audUsuario())
                .audFecha(causal.audFecha())
                .build();
        return repository.upsert(toSave);
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

    public Mono<List<CausalIngresoDetalle>> findNoParametrizadas(Long idCausalIngreso) {
        return repository.findNoParametrizadas(idCausalIngreso);
    }
}
