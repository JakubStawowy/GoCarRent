package com.example.gocarrentspringbootapplication.amqp.controllers;

import com.example.gocarrentspringbootapplication.data.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.finance.dao.RentRepository;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.amqp.api.IMessageService;
import com.example.gocarrentspringbootapplication.data.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.amqp.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.data.po.Announcement;
import com.example.gocarrentspringbootapplication.amqp.po.Message;
import com.example.gocarrentspringbootapplication.finance.enums.RentStatus;
import com.example.gocarrentspringbootapplication.finance.po.Rent;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RequestMapping(value = RentResponseController.BASE_ENDPOINT)
public class RentResponseController {

    public static final String BASE_ENDPOINT = "/api/rents";
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final RentRepository rentRepository;
    private final IMessageService messageService;

    @Autowired
    public RentResponseController(UserRepository userRepository, AnnouncementRepository announcementRepository, RentRepository rentRepository, IMessageService messageService) {
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.rentRepository = rentRepository;
        this.messageService = messageService;
    }

    @PutMapping(EndpointRepository.SEND_02_ENDPOINT)
    public void sendResponseForRentConsent(@RequestParam Long tenantId, @RequestParam Long announcementId, @RequestParam boolean consent) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {

            if (consent) {
                optionalAnnouncement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.RESERVED);
                announcementRepository.save(optionalAnnouncement.get());
            }

            Message message = new Message(
                    RentMessageType.RESPONSE_FOR_RENT_CONSENT,
                    consent,
                    optionalAnnouncement.get().getAuthor(),
                    optionalUser.get(),
                    optionalAnnouncement.get()
            );
            message.setFlag(consent);
            messageService.sendMessage(message);
        }
    }

    @PostMapping(EndpointRepository.SEND_04_ENDPOINT)
    public void sendResponseForRentRealization(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {
            optionalAnnouncement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.RENTED);
            rentRepository.save(new Rent(optionalAnnouncement.get(), optionalUser.get()));
            messageService.sendMessage(new Message(
                    RentMessageType.RESPONSE_FOR_RENT_REALIZATION,
                    null,
                    optionalAnnouncement.get().getAuthor(),
                    optionalUser.get(),
                    optionalAnnouncement.get()
            ));
        }
    }

    @PutMapping(EndpointRepository.SEND_06_ENDPOINT)
    public void sendResponseForRentReturn(@RequestParam Long tenantId, @RequestParam Long rentId, @RequestParam Boolean isReturned) {
        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        if (optionalUser.isPresent() && optionalRent.isPresent()) {

            if (isReturned) {
                optionalRent.get().getAnnouncement().getAnnouncementDetails().setRentStatus(AnnouncementStatus.FREE);
                announcementRepository.save(optionalRent.get().getAnnouncement());
                optionalRent.get().setReturnedAt(new Timestamp(System.currentTimeMillis()));
                optionalRent.get().setRentStatus(RentStatus.FINISHED);
                rentRepository.save(optionalRent.get());
            }
            Message message = new Message(
                    RentMessageType.RESPONSE_FOR_RENT_RETURN,
                    isReturned,
                    optionalRent.get().getAnnouncement().getAuthor(),
                    optionalUser.get(),
                    optionalRent.get().getAnnouncement()
            );
            message.setRentId(rentId);
            messageService.sendMessage(message);
        }
    }
}
