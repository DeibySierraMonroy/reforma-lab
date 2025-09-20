package co.com.activos.jpa.helper;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DynamicSpecificationBuilder<T> {

    private final List<Specification<T>> specs = new ArrayList<>();

    public DynamicSpecificationBuilder<T> addIfPresent(Object value, Specification<T> spec) {
        if (value != null && !(value instanceof String && ((String) value).isBlank())) {
            specs.add(spec);
        }
        return this;
    }

    public Specification<T> build() {
        return specs.stream()
                .reduce(Specification::and)
                .orElse(null);
    }
}

