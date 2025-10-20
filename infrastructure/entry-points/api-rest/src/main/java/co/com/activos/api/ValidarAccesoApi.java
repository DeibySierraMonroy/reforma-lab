package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.usecase.AccesoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class ValidarAccesoApi {

    private final AccesoUseCase accesoUseCase;

    @GetMapping("/validar-acceso")
    public Mono<ApiResponse<Map<String, String>>> validarAcceso(@RequestParam String username, HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("validarAcceso start traceId={} uri={} username={}", traceId, request.getRequestURI(), username);

        return accesoUseCase.findByUsername(username)
                .map(map -> ApiResponse.success(map, request.getRequestURI(), traceId));
    }
}
