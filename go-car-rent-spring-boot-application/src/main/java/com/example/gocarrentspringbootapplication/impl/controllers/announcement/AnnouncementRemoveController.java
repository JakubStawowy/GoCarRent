package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.api.services.IAuthorizeService;
import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public final class AnnouncementRemoveController {

    private final AnnouncementRepository announcementRepository;
    private final IAuthorizeService authorizeService;

    @Autowired
    public AnnouncementRemoveController(AnnouncementRepository announcementRepository, IAuthorizeService authorizeService) {
        this.announcementRepository = announcementRepository;
        this.authorizeService = authorizeService;
    }

    @DeleteMapping(value = "/{id}/remove")
    public ResponseEntity<Boolean> removeAnnouncement(@PathVariable("id") Long id, @RequestParam("userId") Long userId, @RequestParam("password") String password) {
        try {
            if(authorizeService.authorizeUserWithIdAndPassword(userId, password)) {
                announcementRepository.deleteById(id);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (EmptyResultDataAccessException ignored) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
