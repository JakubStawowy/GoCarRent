package com.example.gocarrentspringbootapplication.impl.dao.specifications;

import com.example.gocarrentspringbootapplication.api.dao.specifications.SpecificationTemplate;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.repositories.AnnouncementKeysRepository;
import com.example.gocarrentspringbootapplication.impl.repositories.OperationsRepository;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

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
            switch (key) {
                case AnnouncementKeysRepository.STATUS:
                    predicate = criteriaBuilder.equal(root.get(key), AnnouncementStatus.get(value));
                    break;
                case AnnouncementKeysRepository.TIME_UNIT:
                    predicate = criteriaBuilder.equal(root.get(key), TimeUnit.of(ChronoUnit.valueOf(value)));
                    break;
                case AnnouncementKeysRepository.TITLE:
                    predicate = criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(key)),
                            "%" + value.toLowerCase() + "%"
                    );
                    break;
                default:
                    predicate = criteriaBuilder.equal(root.get(key), value);
                    break;
            }


        else if(operation.equalsIgnoreCase(OperationsRepository.NOT_EQUAL))
            switch (key) {
                case AnnouncementKeysRepository.STATUS:
                    predicate = criteriaBuilder.notEqual(root.get(key), AnnouncementStatus.get(value));
                    break;
                case AnnouncementKeysRepository.TIME_UNIT:
                    predicate = criteriaBuilder.notEqual(root.get(key), TimeUnit.of(ChronoUnit.valueOf(value)));
                    break;
                case AnnouncementKeysRepository.TITLE:
                    predicate = criteriaBuilder.notEqual(criteriaBuilder.lower(root.get(key)), value.toLowerCase());
                    break;
                default:
                    predicate = criteriaBuilder.notEqual(root.get(key), value);
                    break;
            }

        return predicate;
    }

    @Override
    public void validate(String key, String operation, String value) {

    }
}
