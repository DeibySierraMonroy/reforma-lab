package co.com.activos.jpa.acceso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccesoDataRepository extends JpaRepository<AccesoData, String> {

    Optional<AccesoData> findByNombreConstanteAndValor(String user, String valor);
}
