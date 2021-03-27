package com.example.gocarrentspringbootapplication.repositories;

import com.example.gocarrentspringbootapplication.models.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
}
