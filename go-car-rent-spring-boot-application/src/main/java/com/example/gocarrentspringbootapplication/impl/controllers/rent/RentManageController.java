package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/rents")
public class RentManageController {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public RentManageController(RentRepository rentRepository, UserRepository userRepository, AnnouncementRepository announcementRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerRent(@RequestParam("tenantId") Long tenantId, @RequestParam("announcementId") Long announcementId) {

        Optional<User> optionalUser = userRepository.findById(tenantId);
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(announcementId);

        if (optionalUser.isPresent() && optionalAnnouncement.isPresent()) {
            rentRepository.save(new Rent(optionalAnnouncement.get(), optionalUser.get()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/stop")
    public ResponseEntity<?> stopRent(@PathVariable("id") Long rentId) {

        Optional<Rent> optionalRent = rentRepository.findById(rentId);

        if (optionalRent.isPresent()) {
            optionalRent.get().setReturnedAt(new Timestamp(System.currentTimeMillis()));
            rentRepository.save(optionalRent.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
