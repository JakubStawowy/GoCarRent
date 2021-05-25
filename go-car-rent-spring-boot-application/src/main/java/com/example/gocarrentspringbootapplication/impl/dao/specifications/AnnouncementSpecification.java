package com.example.gocarrentspringbootapplication.impl.dao.specifications;

import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public final class AnnouncementSpecification implements Specification<Announcement> {

    private final String key;
    private final String operation;
    private final String value;

    public AnnouncementSpecification(String key, String operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Announcement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (operation.equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(
                    root.get(key),
                    value
            );
        }

        if(operation.equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
                    root.get(key),
                    value
            );
        }

        if(operation.equalsIgnoreCase("=")) {

            if (key.equals("rentStatus")) {
                return criteriaBuilder.equal(
                        root.get(key),
                        AnnouncementStatus.get(value)
                );
            }
            return criteriaBuilder.equal(
                    root.get(key),
                    value
            );
        }

        return null;
    }
}
