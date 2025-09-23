package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.model.solicitud.busqueda.MesaSolicitudCriteria;
import co.com.activos.usecase.BuscadorSolicitudUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class BuscadorApi {

    private final BuscadorSolicitudUseCase buscadorSolicitudUseCase;

    @GetMapping("/buscar")
    public Mono<ApiResponse<List<MesaSolicitudDetalle>>> buscar(
            @RequestParam(required = false) String idMesa,
            @RequestParam(required = false) String tipoDoc,
            @RequestParam(required = false) Long numeroDoc,
            @RequestParam(required = false) Long empTemporal,
            @RequestParam(required = false) String tdTemporal,
            @RequestParam(required = false) String idMesaPersonal,
            HttpServletRequest request) {
        
        final String traceId = (String) request.getAttribute("traceId");
        log.info("buscar start traceId={} uri={} idMesa={} tipoDoc={} numeroDoc={} empTemporal={} tdTemporal={}", 
                traceId, request.getRequestURI(), idMesa, tipoDoc, numeroDoc, empTemporal, tdTemporal);
                
        MesaSolicitudCriteria criteria = MesaSolicitudCriteria.builder()
                .idMesa(idMesa)
                .tipoDoc(tipoDoc)
                .numeroDoc(numeroDoc)
                .empTemporal(empTemporal)
                .tdTemporal(tdTemporal)
                .idMesaPersonal(idMesaPersonal)
                .build();
                
        return buscadorSolicitudUseCase.buscar(criteria)
                .collectList()
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

}
