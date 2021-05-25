package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.api.providers.SpecificationListProviderTemplate;
import com.example.gocarrentspringbootapplication.impl.dao.specifications.AnnouncementSpecification;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class AnnouncementSpecificationListProvider extends SpecificationListProviderTemplate<Announcement> {

    @Override
    public List<Specification<Announcement>> getSpecificationList(String criteria) {
        List<Specification<Announcement>> result = new ArrayList<>();
        for (String[] specArray: getSpecificationSet(criteria))
            result.add(new AnnouncementSpecification(specArray[0], specArray[1], specArray[2]));

        return result;
    }
}
