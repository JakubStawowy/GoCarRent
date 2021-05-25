package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.api.providers.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementDetailsRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import com.example.gocarrentspringbootapplication.impl.providers.AnnouncementDetailsSpecificationListProvider;
import com.example.gocarrentspringbootapplication.impl.providers.AnnouncementSpecificationListProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public final class AnnouncementLoadController {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementDetailsRepository announcementDetailsRepository;
//    private final ISpecificationListProvider<Announcement> specificationListProvider;

    @Autowired
    public AnnouncementLoadController(AnnouncementRepository announcementRepository, AnnouncementDetailsRepository announcementDetailsRepository
            /*,
                                      ISpecificationListProvider<Announcement> specificationListProvider*/) {
        this.announcementRepository = announcementRepository;
        this.announcementDetailsRepository = announcementDetailsRepository;
//        this.specificationListProvider = specificationListProvider;
    }

    @GetMapping(value = {"/", ""})
    public List<AnnouncementTransferObject> getAnnouncements() {
        List<AnnouncementTransferObject> announcements = new ArrayList<>();
        announcementRepository.findAll().forEach(announcement -> {
            if(!announcement.getRentStatus().equals(AnnouncementStatus.BLOCKED))
                announcements.add(new AnnouncementTransferObject(announcement));
        });
        return announcements;
    }

    @GetMapping(value = "/blocked")
    public List<AnnouncementTransferObject> getBlockedAnnouncements() {
        List<AnnouncementTransferObject> announcements = new ArrayList<>();
        announcementRepository.findAll().forEach(announcement -> {
            if(announcement.getRentStatus().equals(AnnouncementStatus.BLOCKED))
                announcements.add(new AnnouncementTransferObject(announcement));
        });
        return announcements;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnouncementTransferObject> getAnnouncement(@PathVariable("id") Long id) {
        Optional<Announcement> optionalAnnouncement = announcementRepository.findById(id);
        return optionalAnnouncement.map(
                announcement->new ResponseEntity<>(new AnnouncementTransferObject(announcement), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/filter")
    public List<AnnouncementTransferObject> getFilteredAnnouncements(@RequestParam("criteria") String criteria) {
        List<AnnouncementTransferObject> announcementTransferObjects = new ArrayList<>();
        try {
            ISpecificationListProvider<Announcement> specificationListProvider = new AnnouncementSpecificationListProvider();
            List<Specification<Announcement>> specificationList = specificationListProvider.getSpecificationList(criteria);

            for (Announcement announcement: announcementRepository.findAll(specificationList.get(0))) {
                announcementTransferObjects.add(new AnnouncementTransferObject(announcement));
            }
        } catch (Exception e) {
            ISpecificationListProvider<AnnouncementDetails> specificationListProvider = new AnnouncementDetailsSpecificationListProvider();
            List<Specification<AnnouncementDetails>> specificationList = specificationListProvider.getSpecificationList(criteria);

            for (AnnouncementDetails announcementDetails: announcementDetailsRepository.findAll(specificationList.get(0))) {
                announcementTransferObjects.add(new AnnouncementTransferObject(announcementDetails.getAnnouncement()));
            }
        }
        return announcementTransferObjects;
    }
}
