package co.com.activos.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {
    private String code;      // Matches domain ErrorCode name or technical identifier
    private String message;   // Human-readable message
    private String details;   // Optional extra detail
    private String traceId;   // Correlation id if available
    private List<FieldViolation> violations; // Optional list of field-level validation errors
}
