package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.api.providers.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.impl.dao.specifications.AnnouncementSpecification;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public final class AnnouncementSpecificationListProvider implements ISpecificationListProvider<Announcement> {

    @Override
    public List<Specification<Announcement>> getSpecificationListWithDecompressedCriteria(Set<String[]> criteria) {
        List<Specification<Announcement>> result = new ArrayList<>();
        for (String[] specArray: criteria)
            result.add(new AnnouncementSpecification(specArray[0], specArray[1], specArray[2]));

        return result;
    }
}
