package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.api.services.IMessageService;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.Message;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/rents")
public class RentResponseController {

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

    @PutMapping("/send/02")
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

    @PostMapping("/send/04")
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

    @PutMapping("/send/06")
    public void sendResponseForRentReturn(@RequestParam Long tenantId, @RequestParam Long rentId, @RequestParam Boolean isReturned) {
        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        if (optionalUser.isPresent() && optionalRent.isPresent()) {

            if (isReturned) {
                optionalRent.get().getAnnouncement().getAnnouncementDetails().setRentStatus(AnnouncementStatus.FREE);
                announcementRepository.save(optionalRent.get().getAnnouncement());
                optionalRent.get().setReturnedAt(new Timestamp(System.currentTimeMillis()));
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
