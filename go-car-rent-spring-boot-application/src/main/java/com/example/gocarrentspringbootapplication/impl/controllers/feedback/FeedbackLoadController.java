package com.example.gocarrentspringbootapplication.impl.controllers.feedback;

import com.example.gocarrentspringbootapplication.impl.dao.repositories.FeedbackRepository;
import com.example.gocarrentspringbootapplication.impl.dto.FeedbackTransferObject;
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
    public List<FeedbackTransferObject> getAllFeedback() {
        List<FeedbackTransferObject> result = new ArrayList<>();
        feedbackRepository.findAll().forEach(feedback -> result.add(new FeedbackTransferObject(feedback)));
        return result;
    }


    @GetMapping("/user/{id}")
    public List<FeedbackTransferObject> getUserFeedback(@PathVariable("id") Long id) {
        List<FeedbackTransferObject> result = new ArrayList<>();
        feedbackRepository.getFeedbacksByUserId(id).forEach(feedback -> result.add(new FeedbackTransferObject(feedback)));

        return result;
    }
}
