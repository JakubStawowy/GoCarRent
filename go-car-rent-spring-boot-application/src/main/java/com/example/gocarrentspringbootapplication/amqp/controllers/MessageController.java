package com.example.gocarrentspringbootapplication.amqp.controllers;

import com.example.gocarrentspringbootapplication.amqp.dao.MessageRepository;
import com.example.gocarrentspringbootapplication.amqp.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.amqp.po.Message;
import com.example.gocarrentspringbootapplication.amqp.api.QueueTemplateRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public MessageController(MessageRepository messageRepository, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/user")
    public List<MessageTransferObject> getMessages(@RequestParam Long userId) {
        List<MessageTransferObject> result = new LinkedList<>();
        for (Message message: messageRepository.getAllByReceiverAndArchived(userId, false)) {
            MessageTransferObject messageBuffer = new MessageTransferObject(message);
            messageBuffer.setRentId(message.getRentId());
            result.add(messageBuffer);
        }
        return result;
    }

    @PutMapping("/{id}/archive")
    public void archiveMessage(@PathVariable("id") Long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        optionalMessage.ifPresent(message->{
            message.setArchived(true);
            messageRepository.save(message);
        });
    }

    @GetMapping(value = "/load")
    public List<MessageTransferObject> loadMessages(@RequestParam Long userId) {

        List<MessageTransferObject> result = new LinkedList<>();
        MessageTransferObject buffer;

        while ((buffer = (MessageTransferObject) rabbitTemplate.receiveAndConvert(QueueTemplateRepository.QUEUE_TEMPLATE.replace(":uid", userId.toString()))) != null) {
            result.add(buffer);
        }

        return result;
    }
}
