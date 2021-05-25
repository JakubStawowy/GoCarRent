package com.example.gocarrentspringbootapplication.impl.dao.specifications;

import com.example.gocarrentspringbootapplication.api.dao.specifications.SpecificationTemplate;
import com.example.gocarrentspringbootapplication.impl.repositories.OperationsRepository;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public final class AnnouncementSpecification extends SpecificationTemplate<Announcement> {

    public AnnouncementSpecification(String key, String operation, String value) {
        super(key, operation, value);
    }

    @Override
    @Nullable
    public Predicate toPredicate(Root<Announcement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate predicate;
        if ((predicate = super.toPredicate(root, criteriaQuery, criteriaBuilder)) != null)
            return predicate;

        if(operation.equalsIgnoreCase(OperationsRepository.EQUALS))
            if (key.equals("rentStatus"))
                predicate = criteriaBuilder.equal(root.get(key), AnnouncementStatus.get(value));
            else
                predicate = criteriaBuilder.equal(root.get(key), value);

        return predicate;
    }

    @Override
    public void validate(String key, String operation, String value) {

    }
}
