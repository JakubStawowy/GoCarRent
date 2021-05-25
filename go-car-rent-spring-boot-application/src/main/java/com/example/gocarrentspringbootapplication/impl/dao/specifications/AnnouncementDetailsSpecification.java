package com.example.gocarrentspringbootapplication.impl.dao.specifications;

import com.example.gocarrentspringbootapplication.api.dao.specifications.SpecificationTemplate;
import com.example.gocarrentspringbootapplication.impl.repositories.OperationsRepository;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public final class AnnouncementDetailsSpecification extends SpecificationTemplate<AnnouncementDetails> {

    public AnnouncementDetailsSpecification(String key, String operation, String value) {
        super(key, operation, value);
    }

    @Override
    public Predicate toPredicate(Root<AnnouncementDetails> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate predicate;
        if ((predicate = super.toPredicate(root, criteriaQuery, criteriaBuilder)) != null)
            return predicate;

        if(operation.equalsIgnoreCase(OperationsRepository.EQUALS))
            if (key.equals("title"))
                predicate = criteriaBuilder.like(root.get(key),"%" + value + "%");
            else
                predicate = criteriaBuilder.equal(root.get(key), value);

        return predicate;
    }

    @Override
    public void validate(String key, String operation, String value) {

    }
}
