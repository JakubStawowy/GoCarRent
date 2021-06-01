package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.MessageRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/rents")
public class RentMessageController {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RentMessageController(UserRepository userRepository, AnnouncementRepository announcementRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.messageRepository = messageRepository;
    }
}
