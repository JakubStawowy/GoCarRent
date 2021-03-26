package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Announcement;
import com.example.gocarrentspringbootapplication.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AnnouncementController {

    private final AnnouncementRepository repository;

    @Autowired
    public AnnouncementController(AnnouncementRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/announcements")
    public List<Announcement> getAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        repository.findAll().forEach(announcements::add);

        return announcements;
    }

    @GetMapping(value = "/announcements/{id}")
    @Nullable
    public Announcement getAnnouncement(@PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = repository.findById(id);
        return optionalAnnouncement.orElse(null);
    }

    @PostMapping(value = "/add")
    public Announcement addAnnouncement(@RequestBody Announcement announcement) {
        repository.save(announcement);
        return announcement;
    }

    @DeleteMapping(value = "/announcements/remove/{id}")
    public String removeAnnouncement(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "Announcement removed successfully";
    }

    @PutMapping(value = "/announcements/edit")
    public Announcement editAnnouncement(@RequestBody Announcement announcement) {
        repository.save(announcement);
        return announcement;
    }
}
