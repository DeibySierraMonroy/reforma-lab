package co.com.activos.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status; // HTTP status code as string (e.g., "200", "400", "422", "500")
    private T data;
    private ErrorDetail error;
    private String path;
    private OffsetDateTime timestamp;
    private String traceId;

    public static <T> ApiResponse<T> success(T data, String path) {
        return success(data, path, null);
    }

    public static <T> ApiResponse<T> error(ErrorDetail error, String path) {
        return error(error, path, null, 500);
    }

    public static <T> ApiResponse<T> success(T data, String path, String traceId) {
        return ApiResponse.<T>builder()
                .status("200")
                .data(data)
                .path(path)
                .traceId(traceId)
                .timestamp(OffsetDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorDetail error, String path, String traceId) {
        return error(error, path, traceId, 500);
    }

    public static <T> ApiResponse<T> error(ErrorDetail error, String path, String traceId, int statusCode) {
        return ApiResponse.<T>builder()
                .status(String.valueOf(statusCode))
                .error(error)
                .path(path)
                .traceId(traceId)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
