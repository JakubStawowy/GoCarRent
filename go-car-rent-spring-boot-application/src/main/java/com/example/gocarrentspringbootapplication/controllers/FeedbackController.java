package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Feedback;
import com.example.gocarrentspringbootapplication.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackRepository repository;

    @Autowired
    public FeedbackController(FeedbackRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/add", consumes = "application/json")
    @Nullable
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return repository.save(feedback);
    }

    @GetMapping("/user/{id}")
    public List<Feedback> getUserFeedback(@PathVariable("id") Long id) {
        return repository.getFeedbacksByUserId(id);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteFeedback(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "Feedback removed successfully";
    }
}
