package co.com.activos.model.acceso.gateway;

import co.com.activos.model.acceso.AccesoIngreso;
import reactor.core.publisher.Mono;

public interface AccesoRepository {
    Mono<AccesoIngreso> findByUser (String user);
}
