package co.com.activos.usecase;

import co.com.activos.model.company.Empresa;
import co.com.activos.model.company.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.List;

@RequiredArgsConstructor
public class EmpresaUseCase {
    private final EmpresaRepository empresaRepository;

    public Mono<List<Empresa>> buscarEmpresas(String nombre, int page, int size) {
        return empresaRepository.buscarEmpresas(nombre, page, size);
    }
}
