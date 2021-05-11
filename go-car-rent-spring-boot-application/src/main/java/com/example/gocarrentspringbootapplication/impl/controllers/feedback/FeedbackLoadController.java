package com.example.gocarrentspringbootapplication.impl.controllers.feedback;

import com.example.gocarrentspringbootapplication.impl.dao.FeedbackRepository;
import com.example.gocarrentspringbootapplication.impl.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackLoadController {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackLoadController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping({"", "/"})
    public List<Feedback> getAllFeedback() {
        List<Feedback> result = new ArrayList<>();
        feedbackRepository.findAll().forEach(result::add);
        return result;
    }


    @GetMapping("/user/{id}")
    public List<Feedback> getUserFeedback(@PathVariable("id") Long id) {
        return feedbackRepository.getFeedbacksByUserId(id);
    }
}
