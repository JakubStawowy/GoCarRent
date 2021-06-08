package com.example.gocarrentspringbootapplication.data.controllers.announcement;

import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import com.example.gocarrentspringbootapplication.security.api.IAuthorizeService;
import com.example.gocarrentspringbootapplication.data.dao.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
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

    @DeleteMapping(value = EndpointRepository.REMOVE_ANNOUNCEMENT_ENDPOINT)
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
