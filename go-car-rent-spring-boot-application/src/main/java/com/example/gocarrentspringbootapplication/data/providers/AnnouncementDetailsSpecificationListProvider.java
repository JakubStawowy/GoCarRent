package com.example.gocarrentspringbootapplication.data.providers;

import com.example.gocarrentspringbootapplication.data.api.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.data.specifications.AnnouncementDetailsSpecification;
import com.example.gocarrentspringbootapplication.data.po.AnnouncementDetails;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public final class AnnouncementDetailsSpecificationListProvider implements ISpecificationListProvider<AnnouncementDetails> {
    @Override
    public List<Specification<AnnouncementDetails>> getSpecificationListWithDecompressedCriteria(final Set<String[]> criteria) {
        List<Specification<AnnouncementDetails>> result = new LinkedList<>();
        for (String[] specArray: criteria)
            result.add(new AnnouncementDetailsSpecification(specArray[0], specArray[1], specArray[2]));

        return result;
    }
}
