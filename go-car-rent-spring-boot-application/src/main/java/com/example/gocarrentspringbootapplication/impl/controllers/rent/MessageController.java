package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.MessageRepository;
import com.example.gocarrentspringbootapplication.impl.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/user")
    public List<MessageTransferObject> getMessages(@RequestParam Long userId) {
        List<MessageTransferObject> result = new LinkedList<>();
        for (Message message: messageRepository.getAllByReceiver(userId)) {
            MessageTransferObject messageBuffer = new MessageTransferObject(message);
            messageBuffer.setRentId(message.getRentId());
            result.add(messageBuffer);
        }
        return result;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteMessage(@PathVariable("id") Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
