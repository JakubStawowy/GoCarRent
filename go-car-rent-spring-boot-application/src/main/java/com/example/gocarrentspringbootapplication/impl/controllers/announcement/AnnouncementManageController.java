package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilderSupervisor;
import com.example.gocarrentspringbootapplication.impl.dao.UserRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public final class AnnouncementManageController {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final IAnnouncementBuilder announcementBuilder;
    private final IAnnouncementBuilderSupervisor announcementBuilderSupervisor;

    @Autowired
    public AnnouncementManageController(
            AnnouncementRepository announcementRepository,
            UserRepository userRepository,
            IAnnouncementBuilder announcementBuilder,
            IAnnouncementBuilderSupervisor announcementBuilderSupervisor
    ) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
        this.announcementBuilder = announcementBuilder;
        this.announcementBuilderSupervisor = announcementBuilderSupervisor;
    }
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Boolean> addAnnouncement(@RequestBody AnnouncementTransferObject announcementTransferObject) {

        Optional<User> author = userRepository.findById(announcementTransferObject.getAuthorId());
        announcementBuilder.refresh();

        if(author.isPresent()) {
            Announcement announcement = announcementBuilderSupervisor.construct(
                    announcementTransferObject,
                    announcementBuilder
            );
            announcementRepository.save(announcement);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<Boolean> editAnnouncement(@RequestBody AnnouncementTransferObject announcementTransferObject, @PathVariable("id") Long id) {

        announcementBuilder.refresh();
        Announcement announcement = announcementBuilderSupervisor.construct(
                announcementTransferObject,
                announcementBuilder
        );

        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        if (optionalAnnouncement.isPresent()) {
            announcement.setId(optionalAnnouncement.get().getId());
            announcement.getAnnouncementDetails().setId(optionalAnnouncement.get().getAnnouncementDetails().getId());
            announcement.setCreatedAt(optionalAnnouncement.get().getCreatedAt());
            announcement.setRentStatus(optionalAnnouncement.get().getRentStatus());
            announcementRepository.save(announcement);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
