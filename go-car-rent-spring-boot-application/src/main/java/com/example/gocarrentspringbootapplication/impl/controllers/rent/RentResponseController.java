package com.example.gocarrentspringbootapplication.impl.controllers.rent;


import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.MessageRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.Message;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.providers.RabbitMessageQueueManager;
import com.example.gocarrentspringbootapplication.impl.repositories.RabbitMessageQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/rents")
public class RentResponseController {


    private final RabbitMessageQueueManager messageQueueManager;
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final RentRepository rentRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RentResponseController(
            RabbitMessageQueueManager messageQueueManager,
            RabbitTemplate rabbitTemplate,
            UserRepository userRepository,
            AnnouncementRepository announcementRepository,
            RentRepository rentRepository,
            MessageRepository messageRepository
    ) {
        this.messageQueueManager = messageQueueManager;
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.rentRepository = rentRepository;
        this.messageRepository = messageRepository;
    }

    @PutMapping("/send/02")
    public void sendResponseForRentConsent(@RequestParam Long tenantId, @RequestParam Long announcementId, @RequestParam boolean consent) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", tenantId.toString());

            if (consent) {
                optionalAnnouncement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.RESERVED);
                announcementRepository.save(optionalAnnouncement.get());
            }

            Message message = new Message(
                    RentMessageType.RESPONSE_FOR_RENT_CONSENT,
                    consent,
                    optionalAnnouncement.get().getAuthor(),
                    optionalUser.get(),
                    optionalUser.get(),
                    optionalAnnouncement.get()
            );
            message.setFlag(consent);
            messageRepository.save(message);

            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new MessageTransferObject(message));
        }
    }

    @PostMapping("/send/04")
    public void sendResponseForRentRealization(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", tenantId.toString());

            optionalAnnouncement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.RENTED);
            rentRepository.save(new Rent(optionalAnnouncement.get(), optionalUser.get()));

            Message message = new Message(
                    RentMessageType.RESPONSE_FOR_RENT_REALIZATION,
                    null,
                    optionalAnnouncement.get().getAuthor(),
                    optionalUser.get(),
                    optionalUser.get(),
                    optionalAnnouncement.get()
            );
            messageRepository.save(message);

            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new MessageTransferObject(message));
        }
    }

    @PutMapping("/send/06")
    public void sendResponseForRentReturn(@RequestParam Long tenantId, @RequestParam Long rentId, @RequestParam Boolean isReturned) {
        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        if (optionalUser.isPresent() && optionalRent.isPresent()) {

            final String queueName = RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", tenantId.toString());

            if (isReturned) {
                optionalRent.get().getAnnouncement().getAnnouncementDetails().setRentStatus(AnnouncementStatus.FREE);
                announcementRepository.save(optionalRent.get().getAnnouncement());

                optionalRent.get().setReturnedAt(new Timestamp(System.currentTimeMillis()));
                rentRepository.save(optionalRent.get());
            }
            System.out.println(optionalUser.get().getId());
            Message message = new Message(
                    RentMessageType.RESPONSE_FOR_RENT_RETURN,
                    isReturned,
                    optionalRent.get().getAnnouncement().getAuthor(),
                    optionalUser.get(),
                    optionalUser.get(),
                    optionalRent.get().getAnnouncement()
            );
            message.setRentId(rentId);
            messageRepository.save(message);
            messageQueueManager.putNewQueue(queueName);
            rabbitTemplate.convertAndSend(queueName, new MessageTransferObject(message));

        }
    }
}
