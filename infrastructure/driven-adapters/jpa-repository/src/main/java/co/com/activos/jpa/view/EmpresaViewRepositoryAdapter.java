package co.com.activos.jpa.view;

import co.com.activos.model.company.repository.EmpresaCausalesViewRepository;
import co.com.activos.model.company.view.EmpresaCausalesView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaViewRepositoryAdapter implements EmpresaCausalesViewRepository {

    private final EmpresaCausaleJpaRepository repository;


    @Override
    public Mono<List<EmpresaCausalesView>> findByNumberDocumentAndTypeDocument(Long numberDocument, String typeDocument, int pagina, int total) {
        return Mono.fromCallable(() -> repository.findByNumeroDocumentoAndTipoDocumento(numberDocument, typeDocument
                        , PageRequest.of(pagina, total)))
                .map(list -> list.stream()
                        .map(EmpresaViewMapper::toEntity)
                        .collect(Collectors.toList()))
                .defaultIfEmpty(Collections.emptyList())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<EmpresaCausalesView> buscarParametrizacion(Long numerDocument, String typeDocument, Long idRelacionDetalle) {
        return Mono.fromCallable(() -> repository
                        .findByNumeroDocumentoAndTipoDocumentoAndIdRelacionDetalle(numerDocument, typeDocument, idRelacionDetalle))
                .map(EmpresaViewMapper::toEntity)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
