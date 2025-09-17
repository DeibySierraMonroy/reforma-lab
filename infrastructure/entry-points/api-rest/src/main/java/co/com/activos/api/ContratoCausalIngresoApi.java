package co.com.activos.api;

import co.com.activos.model.contrato.ContratoCausalIngreso;
import co.com.activos.usecase.ContratoCausalIngresoUseCase;
import co.com.activos.api.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/contratos-ingreso", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContratoCausalIngresoApi {

    private final ContratoCausalIngresoUseCase contratoUseCase;

    @PostMapping
    public Mono<ApiResponse<ContratoCausalIngreso>> crearContratoIngreso(
            @Valid @RequestBody ContratoCausalIngreso contrato,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("crearContratoIngreso start traceId={}", traceId);
        
        return contratoUseCase.guardarContrato(contrato)
                .map(saved -> ApiResponse.success(saved, request.getRequestURI(), traceId));
    }

    @GetMapping("/{id}")
    public Mono<ApiResponse<ContratoCausalIngreso>> obtenerContratoIngreso(
            @PathVariable(required = true) @Min(value = 1, message = "El ID es requerido y debe ser un número positivo") Long id,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("obtenerContratoIngreso start traceId={} id={}", traceId, id);
        
        return contratoUseCase.buscarPorId(id)
                .map(contrato -> ApiResponse.success(contrato, request.getRequestURI(), traceId))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("No se encontró el contrato con ID: " + id))));
    }

    @PutMapping("/{id}")
    public Mono<ApiResponse<ContratoCausalIngreso>> actualizarContratoIngreso(
            @PathVariable Long id,
            @Valid @RequestBody ContratoCausalIngreso contrato,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("actualizarContratoIngreso start traceId={} id={}", traceId, id);
        
        ContratoCausalIngreso contratoActualizado = ContratoCausalIngreso.builder()
                .idContratoCausalIngreso(id)
                .tdcTd(contrato.tdcTd())
                .empNd(contrato.empNd())
                .ctoNumero(contrato.ctoNumero())
                .tdcTdFil(contrato.tdcTdFil())
                .empNdFil(contrato.empNdFil())
                .tdcTdEpl(contrato.tdcTdEpl())
                .eplNd(contrato.eplNd())
                .idRelCausalIngresoDetalle(contrato.idRelCausalIngresoDetalle())
                .descLabor(contrato.descLabor())
                .codigoCargo(contrato.codigoCargo())
                .nombreCargo(contrato.nombreCargo())
                .nomEmpleadoReemplazar(contrato.nomEmpleadoReemplazar())
                .descCausaIncremento(contrato.descCausaIncremento())
                .idCalendarioDetalle(contrato.idCalendarioDetalle())
                .estado(contrato.estado())
                .estadoProceso(contrato.estadoProceso())
                .audUsuario(contrato.audUsuario())
                .audFecha(contrato.audFecha())
                .build();
                
        return contratoUseCase.actualizarContrato(contratoActualizado)
                .map(updated -> ApiResponse.success(updated, request.getRequestURI(), traceId));
    }
}
