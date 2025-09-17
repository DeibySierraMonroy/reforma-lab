package co.com.activos.jpa.contrato;

import co.com.activos.jpa.contrato.mapper.ContratoCausalIngresoMapper;
import co.com.activos.model.contrato.ContratoCausalIngreso;
import co.com.activos.model.contrato.repository.ContratoCausalIngresoRepository;
import co.com.activos.jpa.contrato.jpa.IContratoCausalIngresoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ContratoCausalIngresoRepositoryAdapter implements ContratoCausalIngresoRepository {

    private final IContratoCausalIngresoJpaRepository repository;

    @Override
    public Mono<ContratoCausalIngreso> guardar(ContratoCausalIngreso contrato) {
        return Mono.fromCallable(() -> {
            ContratoCausalIngresoData data = ContratoCausalIngresoMapper.toData(contrato);
            ContratoCausalIngresoData saved = repository.save(data);
            return ContratoCausalIngresoMapper.toDomain(saved);
        });
    }

    @Override
    public Mono<ContratoCausalIngreso> buscarPorId(Long id) {
        return Mono.fromCallable(() -> 
            repository.findById(id)
                .map(ContratoCausalIngresoMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado con id: " + id))
        );
    }

    @Override
    public Mono<ContratoCausalIngreso> actualizar(ContratoCausalIngreso contrato) {
        return buscarPorId(contrato.idContratoCausalIngreso())
            .flatMap(existing -> guardar(contrato));
    }
}
