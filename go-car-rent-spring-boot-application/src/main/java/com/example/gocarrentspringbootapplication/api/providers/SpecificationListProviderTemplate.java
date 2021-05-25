package com.example.gocarrentspringbootapplication.api.providers;

import com.example.gocarrentspringbootapplication.api.providers.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.impl.repositories.OperationsRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class SpecificationListProviderTemplate<T> implements ISpecificationListProvider<T> {

    protected Set<String[]> getSpecificationSet(String criteria) {
        Set<String[]> result = new HashSet<>();
        String[] criteriaArray = criteria.split(";");
        for (String spec: criteriaArray) {
            int i;
            for (i = 0; i < OperationsRepository.OPERATIONS.length; i++)
                if (spec.contains(OperationsRepository.OPERATIONS[i])) break;

            String[] specArray = spec.split(OperationsRepository.OPERATIONS[i]);
            if (specArray.length == 1) result.add(new String[]{specArray[0], OperationsRepository.OPERATIONS[i], ""});
            else result.add(new String[]{specArray[0], OperationsRepository.OPERATIONS[i], specArray[1]});
        }
        return result;
    }
}
