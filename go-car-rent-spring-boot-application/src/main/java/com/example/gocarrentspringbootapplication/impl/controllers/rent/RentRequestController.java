package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.MessageRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.Message;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.providers.RabbitMessageQueueManager;
import com.example.gocarrentspringbootapplication.impl.repositories.RabbitMessageQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/rents")
public class RentRequestController {

    private final RabbitMessageQueueManager messageQueueManager;
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RentRequestController(
            RabbitMessageQueueManager messageQueueManager,
            RabbitTemplate rabbitTemplate,
            UserRepository userRepository,
            AnnouncementRepository announcementRepository,
            MessageRepository messageRepository
    ) {
        this.messageQueueManager = messageQueueManager;
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/send/01")
    public void sendRequestForRentConsent(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", optionalAnnouncement.get().getAuthor().getId().toString());

            Message message = new Message(
                    RentMessageType.REQUEST_FOR_RENT_CONSENT,
                    null,
                    optionalUser.get(),
                    optionalUser.get(),
                    optionalAnnouncement.get().getAuthor(),
                    optionalAnnouncement.get()
            );
            messageRepository.save(message);

            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new MessageTransferObject(
                    message.getId(),
                    RentMessageType.REQUEST_FOR_RENT_CONSENT,
                    tenantId,
                    tenantId,
                    announcementId,
                    null
            ));
        }
    }

    @GetMapping("/send/03")
    public void sendRequestForRentRealization(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", optionalAnnouncement.get().getAuthor().getId().toString());

            Message message = new Message(
                    RentMessageType.REQUEST_FOR_RENT_REALIZATION,
                    null,
                    optionalUser.get(),
                    optionalUser.get(),
                    optionalAnnouncement.get().getAuthor(),
                    optionalAnnouncement.get()
            );
            messageRepository.save(message);
            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new MessageTransferObject(
                    message.getId(),
                    RentMessageType.REQUEST_FOR_RENT_REALIZATION,
                    tenantId,
                    tenantId,
                    announcementId,
                    null
            ));

        }
    }
}
