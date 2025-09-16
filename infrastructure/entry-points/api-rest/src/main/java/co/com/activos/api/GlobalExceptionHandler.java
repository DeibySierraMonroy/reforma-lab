package co.com.activos.api;

import co.com.activos.api.model.ApiResponse;
import co.com.activos.api.model.ErrorDetail;
import co.com.activos.api.model.FieldViolation;
import co.com.activos.model.common.BusinessException;
import co.com.activos.model.common.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.MDC;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex, HttpServletRequest request) {
        HttpStatus status = mapErrorCodeToStatus(ex.getCode());
        ErrorDetail detail = ErrorDetail.builder()
                .code(ex.getCode().name())
                .message(ex.getMessage())
                .build();
        String traceId = getTraceId(request);
        return new ResponseEntity<>(ApiResponse.error(detail, request.getRequestURI(), traceId), status);
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(Exception ex, HttpServletRequest request) {
        ErrorDetail detail = ErrorDetail.builder()
                .code(ErrorCode.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
        String traceId = getTraceId(request);
        return new ResponseEntity<>(ApiResponse.error(detail, request.getRequestURI(), traceId), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> FieldViolation.builder()
                        .field(err.getField())
                        .message(err.getDefaultMessage() != null ? err.getDefaultMessage() : "invalid")
                        .build())
                .collect(Collectors.toList());
        String details = violations.stream()
                .map(v -> v.getField() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        ErrorDetail detail = ErrorDetail.builder()
                .code(ErrorCode.UNPROCESSABLE.name())
                .message("Validation failed")
                .details(details)
                .violations(violations)
                .build();
        String traceId = getTraceId(request);
        return new ResponseEntity<>(ApiResponse.error(detail, request.getRequestURI(), traceId), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        List<FieldViolation> violations = ex.getConstraintViolations().stream()
                .map(cv -> FieldViolation.builder()
                        .field(extractProperty(cv))
                        .message(cv.getMessage())
                        .build())
                .collect(Collectors.toList());
        String details = violations.stream()
                .map(v -> v.getField() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        ErrorDetail detail = ErrorDetail.builder()
                .code(ErrorCode.BAD_REQUEST.name())
                .message("Validation failed")
                .details(details)
                .violations(violations)
                .build();
        String traceId = getTraceId(request);
        return new ResponseEntity<>(ApiResponse.error(detail, request.getRequestURI(), traceId), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex, HttpServletRequest request) {
        ErrorDetail detail = ErrorDetail.builder()
                .code(ErrorCode.INTERNAL_ERROR.name())
                .message("Unexpected error")
                .details(ex.getMessage())
                .build();
        String traceId = getTraceId(request);
        return new ResponseEntity<>(ApiResponse.error(detail, request.getRequestURI(), traceId), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus mapErrorCodeToStatus(ErrorCode code) {
        return switch (code) {
            case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case UNPROCESSABLE -> HttpStatus.UNPROCESSABLE_ENTITY;
            case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private String getTraceId(HttpServletRequest request) {
        Object fromReq = request.getAttribute("traceId");
        if (fromReq instanceof String s && !s.isBlank()) return s;
        String fromMdc = MDC.get("traceId");
        return (fromMdc != null && !fromMdc.isBlank()) ? fromMdc : null;
    }

    private String extractProperty(ConstraintViolation<?> cv) {
        String path = cv.getPropertyPath() != null ? cv.getPropertyPath().toString() : "";
        int idx = path.lastIndexOf('.');
        return idx >= 0 ? path.substring(idx + 1) : path;
    }
}
