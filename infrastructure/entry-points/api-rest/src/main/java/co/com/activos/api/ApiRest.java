package co.com.activos.api;
import co.com.activos.api.model.ApiResponse;
import co.com.activos.model.company.Company;
import co.com.activos.model.company.CompanyDetails;
import co.com.activos.usecase.CompanyUseCase;
import co.com.activos.usecase.CompanyDetailsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;
import java.util.List;


/**
 * 
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ApiRest {

    private final CompanyUseCase companyUseCase;
    private final CompanyDetailsUseCase companyDetailsUseCase;


    @GetMapping(path = "/usecase/path/{numberDocument}")
    public Mono<ApiResponse<String>> commandName(@PathVariable("numberDocument") String numberDocument, HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("commandName start traceId={} numberDocument={} uri={}", traceId, numberDocument, request.getRequestURI());
        return Mono.just(ApiResponse.success("", request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/company/{typeDocument}/{numberDocument}")
    public Mono<ApiResponse<Company>> searchCompany(@PathVariable("typeDocument") String typeDocument,
                                                    @PathVariable("numberDocument") Long numberDocument,
                                                    HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("searchCompany start traceId={} typeDocument={} numberDocument={} uri={}", traceId, typeDocument, numberDocument, request.getRequestURI());
        return companyUseCase.searchCompany(numberDocument, typeDocument)
                .map(company -> ApiResponse.success(company, request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/companies")
    public Mono<ApiResponse<List<Company>>> findAllCompanies(HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("findAllCompanies start traceId={} uri={}", traceId, request.getRequestURI());
        return companyUseCase.findAll()
                .map(list -> ApiResponse.success(list, request.getRequestURI(), traceId));
    }

    @GetMapping(path = "/company/{typeDocument}/{numberDocument}/details")
    public Mono<ApiResponse<List<CompanyDetails>>> getCompanyDetails(
            @PathVariable("typeDocument") String typeDocument,
            @PathVariable("numberDocument") Long numberDocument,
            HttpServletRequest request) {
        final String traceId = (String) request.getAttribute("traceId");
        log.info("getCompanyDetails start traceId={} typeDocument={} numberDocument={} uri={}", 
                traceId, typeDocument, numberDocument, request.getRequestURI());
        return companyDetailsUseCase.getCompanyDetails(typeDocument, numberDocument)
                .collectList()
                .map(details -> ApiResponse.success(details, request.getRequestURI(), traceId));
    }
}

