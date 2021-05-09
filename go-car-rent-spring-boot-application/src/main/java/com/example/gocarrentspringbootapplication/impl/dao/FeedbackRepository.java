package com.example.gocarrentspringbootapplication.impl.dao;

import com.example.gocarrentspringbootapplication.impl.models.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    List<Feedback> getFeedbacksByUserId(Long id);
}
