package com.example.gocarrentspringbootapplication.impl.controllers.message;

import com.example.gocarrentspringbootapplication.impl.models.Message;
import com.example.gocarrentspringbootapplication.impl.dao.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public final class MessageLoaderController {

    private final MessageRepository repository;

    @Autowired
    public MessageLoaderController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public List<Message> getUserMessages(@PathVariable("id") Long id, @RequestParam("receiver_id") Long receiverId) {
        return repository.getAllBySenderAndReceiver(id, receiverId);
    }

    @GetMapping("/all/{id}")
    public List<Message> getAllUserMessages(@PathVariable("id") Long id) {
        return repository.getAllBySenderAndReceiver(id, id);
    }
}