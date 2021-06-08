package com.example.gocarrentspringbootapplication.amqp.controllers;

import com.example.gocarrentspringbootapplication.data.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.finance.dao.RentRepository;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.amqp.api.IMessageService;
import com.example.gocarrentspringbootapplication.amqp.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.data.po.Announcement;
import com.example.gocarrentspringbootapplication.amqp.po.Message;
import com.example.gocarrentspringbootapplication.finance.po.Rent;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RequestMapping(value = "/api/rents")
public class RentRequestController {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final RentRepository rentRepository;
    private final IMessageService messageService;

    public RentRequestController(UserRepository userRepository, AnnouncementRepository announcementRepository, RentRepository rentRepository, IMessageService messageService) {
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.rentRepository = rentRepository;
        this.messageService = messageService;
    }

    @GetMapping(EndpointRepository.SEND_01_ENDPOINT)
    public void sendRequestForRentConsent(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {
            messageService.sendMessage(new Message(
                    RentMessageType.REQUEST_FOR_RENT_CONSENT,
                    null,
                    optionalUser.get(),
                    optionalAnnouncement.get().getAuthor(),
                    optionalAnnouncement.get()
            ));
        }
    }

    @GetMapping(EndpointRepository.SEND_03_ENDPOINT)
    public void sendRequestForRentRealization(@RequestParam Long tenantId, @RequestParam Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);
        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {
            messageService.sendMessage(new Message(
                    RentMessageType.REQUEST_FOR_RENT_REALIZATION,
                    null,
                    optionalUser.get(),
                    optionalAnnouncement.get().getAuthor(),
                    optionalAnnouncement.get()
            ));
        }
    }

    @GetMapping(EndpointRepository.SEND_05_ENDPOINT)
    public void sendRequestForRentReturn(@RequestParam Long tenantId, @RequestParam Long rentId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        if (optionalUser.isPresent() && optionalRent.isPresent()) {
            Message message = new Message(
                    RentMessageType.REQUEST_FOR_RENT_RETURN,
                    null,
                    optionalUser.get(),
                    optionalRent.get().getAnnouncement().getAuthor(),
                    optionalRent.get().getAnnouncement()
            );
            message.setRentId(optionalRent.get().getId());
            messageService.sendMessage(message);
        }
    }
}
