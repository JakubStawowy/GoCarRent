package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Feedback;
import com.example.gocarrentspringbootapplication.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") Long id) {
        try{
            repository.deleteById(id);
            return new ResponseEntity<>("Feedback removed successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>("Feedback with this id does not exists", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<String> editFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
        Optional<Feedback> optionalFeedback = repository.findById(id);
        if(optionalFeedback.isPresent()) {
            feedback.setId(id);
            repository.save(feedback);
            return new ResponseEntity<>("Feedback edited successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Feedback with this id does not exists", HttpStatus.NOT_FOUND);
    }
}
