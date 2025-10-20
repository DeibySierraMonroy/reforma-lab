package co.com.activos.jpa.acceso;

import co.com.activos.model.acceso.AccesoIngreso;
import co.com.activos.model.acceso.gateway.AccesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AccesoRepositoryAdapter implements AccesoRepository {

    private final AccesoDataRepository repository;

    @Override
    public Mono<AccesoIngreso> findByUser(String user) {

        return Mono.fromCallable(() -> repository.findByNombreConstanteAndValor("ACCGCPCAUS", user))
                .flatMap(optional -> optional
                        .map(entity -> Mono.just(toDomain(entity)))
                        .orElseGet(Mono::empty)
                );
    }

    private AccesoIngreso toDomain(AccesoData entity) {
        return AccesoIngreso.builder()
                .nombreConstante(entity.getNombreConstante())
                .valor(entity.getValor())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
