package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.company.EmpresaCausales;
import co.com.activos.usecase.EmpresaCausalUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(value = "/api/empresa-causales", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmpresaCausalesApi {

    private final EmpresaCausalUseCase empresaCausalUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<EmpresaCausales>> guardar(@RequestBody EmpresaCausales empresaCausales , HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("guardar start traceId={} uri={} empresaCausales={}", traceId, request.getRequestURI(), empresaCausales);
        return empresaCausalUseCase.guardar(empresaCausales)
                .map(saved -> ApiResponse.success(saved, request.getRequestURI(), traceId));
    }


}
