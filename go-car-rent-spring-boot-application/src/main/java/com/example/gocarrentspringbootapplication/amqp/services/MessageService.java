package com.example.gocarrentspringbootapplication.amqp.services;

import com.example.gocarrentspringbootapplication.amqp.api.IQueueService;
import com.example.gocarrentspringbootapplication.amqp.dao.MessageRepository;
import com.example.gocarrentspringbootapplication.amqp.api.IMessageService;
import com.example.gocarrentspringbootapplication.amqp.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.amqp.po.Message;
import com.example.gocarrentspringbootapplication.repositories.QueueTemplateRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public final class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final IQueueService queueService;
    private final RabbitTemplate rabbitTemplate;

    public MessageService(MessageRepository messageRepository, IQueueService queueService, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.queueService = queueService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(final Message message) {
        final String queueName = QueueTemplateRepository.QUEUE_TEMPLATE.replace(":uid", message.getReceiver().getId().toString());
        messageRepository.save(message);
        queueService.putNewQueue(queueName);
        MessageTransferObject messageTransferObject = new MessageTransferObject(message);
        messageTransferObject.setRentId(message.getRentId());
        rabbitTemplate.convertAndSend(queueName, messageTransferObject);
    }
}
