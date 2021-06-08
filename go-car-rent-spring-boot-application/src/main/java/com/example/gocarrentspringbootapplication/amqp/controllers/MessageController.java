package com.example.gocarrentspringbootapplication.amqp.controllers;

import com.example.gocarrentspringbootapplication.amqp.api.IQueueService;
import com.example.gocarrentspringbootapplication.amqp.dao.MessageRepository;
import com.example.gocarrentspringbootapplication.amqp.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.amqp.po.Message;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import com.example.gocarrentspringbootapplication.repositories.QueueTemplateRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;
    private final IQueueService queueService;

    @Autowired
    public MessageController(MessageRepository messageRepository, RabbitTemplate rabbitTemplate, IQueueService queueService) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queueService = queueService;
    }

    @GetMapping(EndpointRepository.USER_MESSAGES_ENDPOINT)
    public List<MessageTransferObject> getMessages(@RequestParam Long userId, @RequestParam Boolean archived) {
        List<MessageTransferObject> result = new LinkedList<>();
        for (Message message: messageRepository.getAllByReceiverAndArchived(userId, archived)) {
            MessageTransferObject messageBuffer = new MessageTransferObject(message);
            messageBuffer.setRentId(message.getRentId());
            result.add(messageBuffer);
        }
        return result;
    }

    @PutMapping(EndpointRepository.ARCHIVE_MESSAGE_ENDPOINT)
    public void archiveMessage(@PathVariable("id") Long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        optionalMessage.ifPresent(message->{
            message.setArchived(true);
            messageRepository.save(message);
        });
    }

    @GetMapping(value = EndpointRepository.LOAD_MESSAGES_ENDPOINT)
    public List<MessageTransferObject> loadMessages(@RequestParam Long userId) {

        List<MessageTransferObject> result = new LinkedList<>();
        MessageTransferObject buffer;
        final String queueName = QueueTemplateRepository.QUEUE_TEMPLATE.replace(":uid", userId.toString());
        queueService.putNewQueue(queueName);
        while ((buffer = (MessageTransferObject) rabbitTemplate.receiveAndConvert(queueName)) != null) {
            result.add(buffer);
        }

        return result;
    }
}
