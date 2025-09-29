package co.com.activos.jpa.causal;

import co.com.activos.jpa.helper.AdapterOperations;
import co.com.activos.model.causal.CausalIngresoDetalle;
import co.com.activos.model.causal.repository.CausalIngresoDetalleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CausalIngresoDetalleRepositoryAdapter extends AdapterOperations<CausalIngresoDetalle, CausalIngresoDetalleData, Long, CausalIngresoDetalleDataRepository>
        implements CausalIngresoDetalleRepository {

    protected CausalIngresoDetalleRepositoryAdapter(CausalIngresoDetalleDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CausalIngresoDetalle.CausalIngresoDetalleBuilder.class).build());
    }

    @Override
    protected CausalIngresoDetalleData toData(CausalIngresoDetalle entity) {
        CausalIngresoDetalleData data = new CausalIngresoDetalleData();
        data.setIdCausalIngresoDet(entity.idCausalIngresoDet());
        data.setDescCausalIngresoDet(entity.descCausalIngresoDet());
        data.setEstado(entity.estado());
        data.setAudUsuario(entity.audUsuario());
        data.setAudFecha(entity.audFecha());
        return data;
    }

    @Override
    public Mono<List<CausalIngresoDetalle>> listAll() {
        return Mono.fromCallable(super::findAll);
    }

    @Override
    public Mono<CausalIngresoDetalle> getById(Long id) {
        return Mono.fromCallable(() -> super.findById(id))
                .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Mono<CausalIngresoDetalle> upsert(CausalIngresoDetalle causal) {
        return Mono.fromCallable(() -> super.save(causal));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return Mono.fromRunnable(() -> repository.deleteById(id));
    }

    @Override
    public Mono<List<CausalIngresoDetalle>> findNoParametrizadas(Long idCausalIngreso, Long empresa) {
        return Mono.fromCallable(() -> repository.findNoParametrizadas(idCausalIngreso ,empresa))
                .map(list -> list.stream()
                        .map(this::toDomain)
                        .collect(Collectors.toList()));
    }

    @Override
    public Mono<CausalIngresoDetalle> findByDescCausalIngresoDet(String descCausalIngresoDet) {
        return Mono.fromCallable(() -> repository.findByDescCausalIngresoDet(descCausalIngresoDet.toUpperCase()))
                .map(this::toDomain);
    }

    private CausalIngresoDetalle toDomain(CausalIngresoDetalleData data) {
        return CausalIngresoDetalle.builder()
                .idCausalIngresoDet(data.getIdCausalIngresoDet())
                .descCausalIngresoDet(data.getDescCausalIngresoDet())
                .estado(data.getEstado())
                .audUsuario(data.getAudUsuario())
                .audFecha(data.getAudFecha())
                .build();
    }
}
