package co.com.activos.model.company.repository;

import co.com.activos.model.company.view.EmpresaCausalesView;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmpresaCausalesViewRepository {
    Mono<List<EmpresaCausalesView>> findByNumberDocumentAndTypeDocument(Long numberDocument, String typeDocument, int pagina, int total);
    Mono<EmpresaCausalesView> buscarParametrizacion(Long numerDocument, String typeDocument, Long idRelacionDetalle);
}
