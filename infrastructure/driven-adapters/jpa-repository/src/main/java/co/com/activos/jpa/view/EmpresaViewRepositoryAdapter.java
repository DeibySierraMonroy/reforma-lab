package co.com.activos.jpa.view;

import co.com.activos.jpa.helper.SpecificationUtils;
import co.com.activos.model.company.repository.EmpresaCausalesViewRepository;
import co.com.activos.model.company.view.EmpresaCausalesView;
import co.com.activos.model.solicitud.busqueda.CausalRelacionCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaViewRepositoryAdapter implements EmpresaCausalesViewRepository {

    private final EmpresaCausaleJpaRepository repository;


    @Override
    public Flux<EmpresaCausalesView> buscar(CausalRelacionCriteria causalRelacionCriteria) {
        Specification<EmpresaCausalesViewData> spec = SpecificationUtils.buildFromDto(causalRelacionCriteria, EmpresaCausalesViewData.class);
        PageRequest pageRequest = PageRequest.of(causalRelacionCriteria.getPagina(), causalRelacionCriteria.getSize());
        return Flux.fromIterable(
                repository.findAll(spec)
                        .stream()
                        .map(EmpresaViewMapper::toEntity)
                        .toList()
        );

    }

    @Override
    public Mono<EmpresaCausalesView> buscarParametrizacion(Long numerDocument, String typeDocument, Long idRelacionDetalle) {
        return Mono.fromCallable(() -> repository
                        .findByNumeroDocumentoAndTipoDocumentoAndIdRelacionDetalle(numerDocument, typeDocument, idRelacionDetalle))
                .map(EmpresaViewMapper::toEntity)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
