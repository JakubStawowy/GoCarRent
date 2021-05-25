package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.api.providers.SpecificationListProviderTemplate;
import com.example.gocarrentspringbootapplication.impl.dao.specifications.AnnouncementDetailsSpecification;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class AnnouncementDetailsSpecificationListProvider extends SpecificationListProviderTemplate<AnnouncementDetails> {
    @Override
    public List<Specification<AnnouncementDetails>> getSpecificationList(String criteria) {
        List<Specification<AnnouncementDetails>> result = new ArrayList<>();
        for (String[] specArray: getSpecificationSet(criteria))
            result.add(new AnnouncementDetailsSpecification(specArray[0], specArray[1], specArray[2]));

        return result;
    }
}
