package co.com.activos.model.company;

import lombok.Builder;

@Builder
public record Company(Long numberDocument, String typeDocument, String name, String status) {
}
