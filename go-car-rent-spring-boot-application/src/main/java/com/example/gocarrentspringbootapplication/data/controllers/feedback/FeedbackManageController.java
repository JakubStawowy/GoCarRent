package com.example.gocarrentspringbootapplication.data.controllers.feedback;

import com.example.gocarrentspringbootapplication.data.po.Feedback;
import com.example.gocarrentspringbootapplication.data.dao.FeedbackRepository;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(FeedbackManageController.BASE_ENDPOINT)
public final class FeedbackManageController {

    public static final String BASE_ENDPOINT = "/api/feedback";
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackManageController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping(path = EndpointRepository.ADD_FEEDBACK_ENDPOINT, consumes = "application/json")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }


    @DeleteMapping(EndpointRepository.DELETE_FEEDBACK_ENDPOINT)
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") Long id) {
        try{
            feedbackRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = EndpointRepository.EDIT_FEEDBACK_ENDPOINT, consumes = "application/json")
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
