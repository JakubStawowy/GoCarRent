package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.api.providers.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.impl.dao.specifications.AnnouncementSpecification;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class AnnouncementSpecificationListProvider implements ISpecificationListProvider<Announcement> {
    private final String[] operations = {"<", ">", "="};
    @Override
    public List<Specification<Announcement>> getSpecificationList(String criteria) {
        List<Specification<Announcement>> result = new ArrayList<>();
        String[] criteriaArray = criteria.split(";");
        for (String spec: criteriaArray) {
            int i;
            for (i = 0; i < operations.length; i++)
                if (spec.contains(operations[i])) break;

            String[] specArray = spec.split(operations[i]);
            result.add(new AnnouncementSpecification(specArray[0], operations[i], specArray[1]));

        }
        return result;
    }
}
