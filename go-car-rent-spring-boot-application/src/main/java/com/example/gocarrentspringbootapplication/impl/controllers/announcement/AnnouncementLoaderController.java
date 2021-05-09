package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.impl.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public class AnnouncementLoaderController {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementLoaderController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @GetMapping(value = {"/", ""})
    public List<Announcement> getAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        announcementRepository.findAll().forEach(announcements::add);

        return announcements;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        return optionalAnnouncement.map(announcement->new ResponseEntity<>(announcement, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
