package com.basis.RRM.service.filter;

import org.springframework.data.jpa.domain.Specification;

public interface EntityFilter<E> {
    Specification<E> filtrar();
}
