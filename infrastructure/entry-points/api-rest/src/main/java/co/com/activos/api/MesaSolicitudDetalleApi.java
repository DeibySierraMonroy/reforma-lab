package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.solicitud.MesaSolicitudDetalle;
import co.com.activos.usecase.MesaSolicitudDetalleUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mesa-solicitud-detalle")
public class MesaSolicitudDetalleApi {

    private final MesaSolicitudDetalleUseCase mesaSolicitudDetalleUseCase;

    @GetMapping("/{idMesaPersonal}")
    public Mono<ResponseEntity<List<MesaSolicitudDetalle>>> buscarPorIdMesaPersonal(
            @PathVariable String idMesaPersonal,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("buscarPorIdMesaPersonal start traceId={} idMesaPersonal={} uri={}",
                traceId, idMesaPersonal, request.getRequestURI());

        return mesaSolicitudDetalleUseCase.buscarPorIdMesaPersonal(idMesaPersonal)
                .collectList()
                .map(detalles -> {
                    if (detalles.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(detalles);
                });
    }
}
