package com.example.gocarrentspringbootapplication.impl.controllers.feedback;

import com.example.gocarrentspringbootapplication.impl.models.Feedback;
import com.example.gocarrentspringbootapplication.impl.dao.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public final class FeedbackManageController {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackManageController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping(path = "/add", consumes = "application/json")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") Long id) {
        try{
            feedbackRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<?> editFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if(optionalFeedback.isPresent()) {
            feedback.setId(id);
            feedbackRepository.save(feedback);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
