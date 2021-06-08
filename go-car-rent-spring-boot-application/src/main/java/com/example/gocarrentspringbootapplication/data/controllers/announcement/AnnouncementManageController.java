package com.example.gocarrentspringbootapplication.data.controllers.announcement;

import com.example.gocarrentspringbootapplication.data.api.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.data.api.IAnnouncementBuilderSupervisor;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.data.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.data.po.Announcement;
import com.example.gocarrentspringbootapplication.data.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
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
    @PostMapping(value = EndpointRepository.ADD_ANNOUNCEMENTS_ENDPOINT, consumes = "application/json")
    public ResponseEntity<?> addAnnouncement(@RequestBody AnnouncementTransferObject announcementTransferObject) {

        Optional<User> author = userRepository.findById(announcementTransferObject.getAuthorId());
        announcementBuilder.refresh();

        if(author.isPresent()) {
            Announcement announcement = announcementBuilderSupervisor.construct(
                    announcementTransferObject,
                    announcementBuilder
            );
            announcementRepository.save(announcement);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = EndpointRepository.EDIT_ANNOUNCEMENT_ENDPOINT, consumes = "application/json")
    public ResponseEntity<?> editAnnouncement(@RequestBody AnnouncementTransferObject announcementTransferObject, @PathVariable("id") Long id) {

        announcementBuilder.refresh();
        Announcement announcement = announcementBuilderSupervisor.construct(
                announcementTransferObject,
                announcementBuilder
        );

        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        if (optionalAnnouncement.isPresent()) {
            announcement.setId(optionalAnnouncement.get().getId());
            announcement.getAnnouncementDetails().setId(optionalAnnouncement.get().getAnnouncementDetails().getId());
            announcement.getAnnouncementDetails().setCreatedAt(optionalAnnouncement.get().getAnnouncementDetails().getCreatedAt());
            announcement.getAnnouncementDetails().setRentStatus(optionalAnnouncement.get().getAnnouncementDetails().getRentStatus());
            announcementRepository.save(announcement);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(EndpointRepository.BLOCK_ANNOUNCEMENT_ENDPOINT)
    public ResponseEntity<?> blockAnnouncement(@PathVariable("id") Long announcementId) {
        Optional<Announcement> announcement = announcementRepository.findById(announcementId);
        if(announcement.isPresent()) {
            announcement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.BLOCKED);
            announcementRepository.save(announcement.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(EndpointRepository.UNLOCK_ANNOUNCEMENT_ENDPOINT)
    public ResponseEntity<?> unlockAnnouncement(@PathVariable("id") Long announcementId) {
        Optional<Announcement> announcement = announcementRepository.findById(announcementId);
        if(announcement.isPresent()) {
            announcement.get().getAnnouncementDetails().setRentStatus(AnnouncementStatus.FREE);
            announcementRepository.save(announcement.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
