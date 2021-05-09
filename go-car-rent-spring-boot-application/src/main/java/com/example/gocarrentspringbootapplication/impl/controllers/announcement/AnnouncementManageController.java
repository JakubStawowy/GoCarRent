package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.impl.dao.UserRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import com.example.gocarrentspringbootapplication.impl.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public final class AnnouncementManageController {

    private final AnnouncementRepository repository;
    private final UserRepository userRepository;
    private final IAnnouncementBuilder announcementBuilder;

    @Autowired
    public AnnouncementManageController(
            AnnouncementRepository repository,
            UserRepository userRepository,
            IAnnouncementBuilder announcementBuilder
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.announcementBuilder = announcementBuilder;
    }
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Boolean> addAnnouncement(@RequestBody AnnouncementTransferObject announcementTransferObject) {

        Optional<User> author = userRepository.findById(announcementTransferObject.getAuthorId());

        if(author.isPresent()) {
            Announcement announcement = announcementBuilder
                    .setTitle(announcementTransferObject.getTitle())
                    .setAmount(announcementTransferObject.getAmount())
                    .setCurrency(announcementTransferObject.getCurrency())
                    .setTimeUnit(announcementTransferObject.getTimeUnit())
                    .setCarBrand(announcementTransferObject.getCarBrand())
                    .setCarModel(announcementTransferObject.getCarModel())
                    .setAuthor(announcementTransferObject.getAuthorId())
                    .build();

            repository.save(announcement);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}/remove")
    public ResponseEntity<Boolean> removeAnnouncement(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<Boolean> editAnnouncement(@RequestBody AnnouncementDetails details, @PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = repository.findById(id);
        if (optionalAnnouncement.isPresent()) {
            Announcement announcement = optionalAnnouncement.get();
            announcement.setAnnouncementDetails(details);
            repository.save(announcement);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
