package co.com.activos.jpa.helper;

import co.com.activos.model.solicitud.busqueda.FilterBy;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;

public class SpecificationUtils {

    public static <T> Specification<T> buildFromDto(Object criteriaDto, Class<T> entityClass) {
        DynamicSpecificationBuilder<T> builder = new DynamicSpecificationBuilder<>();

        Arrays.stream(criteriaDto.getClass().getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(FilterBy.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(criteriaDto);

                    if (value != null && !(value instanceof String s && s.isBlank())) {
                        String entityField = field.getAnnotation(FilterBy.class).value();

                        boolean exists = Arrays.stream(entityClass.getDeclaredFields())
                                .anyMatch(f -> f.getName().equals(entityField));

                        if (!exists) {
                            throw new IllegalArgumentException("El campo '" + entityField +
                                    "' no existe en la entidad " + entityClass.getSimpleName());
                        }

                        builder.addIfPresent(value, (root, query, cb) ->
                                cb.equal(root.get(entityField), value)
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error leyendo campo " + field.getName(), e);
                }
            }
        });

        return builder.build();
    }
}

