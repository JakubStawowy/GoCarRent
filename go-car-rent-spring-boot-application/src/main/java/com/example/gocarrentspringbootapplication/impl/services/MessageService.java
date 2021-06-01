package com.example.gocarrentspringbootapplication.impl.services;

import com.example.gocarrentspringbootapplication.api.dao.repositories.MessageRepository;
import com.example.gocarrentspringbootapplication.api.services.IMessageService;
import com.example.gocarrentspringbootapplication.impl.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Message;
import com.example.gocarrentspringbootapplication.impl.repositories.RabbitMessageQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final RabbitMessageQueueService messageQueueManager;
    private final RabbitTemplate rabbitTemplate;

    public MessageService(MessageRepository messageRepository, RabbitMessageQueueService messageQueueManager, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.messageQueueManager = messageQueueManager;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Message message) {
        final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", message.getReceiver().getId().toString());
        messageRepository.save(message);
        messageQueueManager.putNewQueue(queueName);
        MessageTransferObject messageTransferObject = new MessageTransferObject(message);
        messageTransferObject.setRentId(message.getRentId());
        rabbitTemplate.convertAndSend(queueName, messageTransferObject);
    }
}
