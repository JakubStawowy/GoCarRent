package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Announcement;
import com.example.gocarrentspringbootapplication.models.AnnouncementDetails;
import com.example.gocarrentspringbootapplication.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public class AnnouncementController {

    private final AnnouncementRepository repository;

    @Autowired
    public AnnouncementController(AnnouncementRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = {"/", ""})
    public List<Announcement> getAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        repository.findAll().forEach(announcements::add);

        return announcements;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = repository.findById(id);
        return optionalAnnouncement.map(announcement->new ResponseEntity<>(announcement, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addAnnouncement(@RequestBody Announcement announcement) {
        repository.save(announcement);
        return new ResponseEntity<>("Announcement added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/remove")
    public ResponseEntity<String> removeAnnouncement(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Announcement removed successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>("Announcement with this id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<String> editAnnouncement(@RequestBody AnnouncementDetails details, @PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = repository.findById(id);
        if (optionalAnnouncement.isPresent()) {
            Announcement announcement = optionalAnnouncement.get();
            announcement.setAnnouncementDetails(details);
            repository.save(announcement);
            return new ResponseEntity<>("Announcement edited successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
