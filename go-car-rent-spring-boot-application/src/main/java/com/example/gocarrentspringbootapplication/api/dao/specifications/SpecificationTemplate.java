package com.example.gocarrentspringbootapplication.api.dao.specifications;

import com.example.gocarrentspringbootapplication.impl.repositories.OperationsRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class SpecificationTemplate<T> implements Specification<T> {

    protected final String key;
    protected final String operation;
    protected final String value;

    public SpecificationTemplate(String key, String operation, String value) {
        validate(key, operation, value);
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public abstract void validate(String key, String operation, String value);

    @Override
    @Nullable
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (operation.equalsIgnoreCase(OperationsRepository.GREATER_THAN))
            return criteriaBuilder.greaterThan(root.get(key), value);

        if(operation.equalsIgnoreCase(OperationsRepository.LESS_THAN))
            return criteriaBuilder.lessThan(root.get(key), value);

        if (operation.equalsIgnoreCase(OperationsRepository.GREATER_THAN_EQUALS))
            return criteriaBuilder.greaterThanOrEqualTo(root.get(key), value);

        if(operation.equalsIgnoreCase(OperationsRepository.LESS_THAN_EQUALS))
            return criteriaBuilder.lessThanOrEqualTo(root.get(key), value);

        return null;
    }
}
