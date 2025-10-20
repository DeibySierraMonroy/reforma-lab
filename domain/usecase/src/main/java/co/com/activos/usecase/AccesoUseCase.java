package co.com.activos.usecase;

import co.com.activos.model.acceso.gateway.AccesoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class AccesoUseCase {

    private final AccesoRepository repository;

    public Mono<Map<String, String>> findByUsername(String username) {
        return repository.findByUser(username)
                .map(access -> Map.of("role", "write"))
                .defaultIfEmpty(Map.of("role", "view"));
    }
}
