package com.example.gocarrentspringbootapplication.impl.controllers.rent;


import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.RentMessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.UserTransferObject;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
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
public class RentResponseController {


    private final RabbitMessageQueueManager messageQueueManager;
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public RentResponseController(RabbitMessageQueueManager messageQueueManager, RabbitTemplate rabbitTemplate, UserRepository userRepository, AnnouncementRepository announcementRepository) {
        this.messageQueueManager = messageQueueManager;
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
    }

    @GetMapping("/send/02")
    public void sendResponseForRentConsent(@RequestParam Long tenantId, @RequestParam Long announcementId, @RequestParam boolean consent) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", tenantId.toString());

            if (consent) {
                optionalAnnouncement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.RESERVED);
                announcementRepository.save(optionalAnnouncement.get());
            }

            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new RentMessageTransferObject(
                    RentMessageType.RESPONSE_FOR_RENT_CONSENT,
                    optionalAnnouncement.get().getAuthor().getId(),
                    tenantId,
                    announcementId,
                    consent
            ));
        }
    }

    @GetMapping("/send/04")
    public void sendResponseForRentRealization(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", tenantId.toString());

            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new RentMessageTransferObject(
                    RentMessageType.RESPONSE_FOR_RENT_REALIZATION,
                    optionalAnnouncement.get().getAuthor().getId(),
                    tenantId,
                    announcementId,
                    null
            ));
        }
    }
}
