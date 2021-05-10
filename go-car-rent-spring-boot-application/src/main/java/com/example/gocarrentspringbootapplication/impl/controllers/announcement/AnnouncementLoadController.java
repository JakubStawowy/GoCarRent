package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.impl.dao.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
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
public class AnnouncementLoadController {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementLoadController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @GetMapping(value = {"/", ""})
    public List<AnnouncementTransferObject> getAnnouncements() {
        List<AnnouncementTransferObject> announcements = new ArrayList<>();
        announcementRepository.findAll().forEach(announcement -> announcements.add(new AnnouncementTransferObject(announcement)));
        return announcements;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnouncementTransferObject> getAnnouncement(@PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        return optionalAnnouncement.map(
                announcement->new ResponseEntity<>(new AnnouncementTransferObject(announcement), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
