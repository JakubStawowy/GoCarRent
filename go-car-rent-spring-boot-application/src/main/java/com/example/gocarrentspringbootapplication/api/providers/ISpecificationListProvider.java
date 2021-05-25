package com.example.gocarrentspringbootapplication.api.providers;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ISpecificationListProvider<T> {
    List<Specification<T>> getSpecificationList(String criteria);
}
