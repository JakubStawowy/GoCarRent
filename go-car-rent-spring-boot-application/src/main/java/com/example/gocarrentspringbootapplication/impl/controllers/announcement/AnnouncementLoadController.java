package com.example.gocarrentspringbootapplication.impl.controllers.announcement;

import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementDetailsRepository;
import com.example.gocarrentspringbootapplication.api.dao.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.api.providers.ISpecificationListProvider;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/announcements")
public final class AnnouncementLoadController {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementDetailsRepository announcementDetailsRepository;
    private final ISpecificationListProvider<AnnouncementDetails> specificationListProvider;
    @Autowired
    public AnnouncementLoadController(
            AnnouncementRepository announcementRepository,
            AnnouncementDetailsRepository announcementDetailsRepository,
            ISpecificationListProvider<AnnouncementDetails> specificationListProvider
    ) {
        this.announcementRepository = announcementRepository;
        this.announcementDetailsRepository = announcementDetailsRepository;
        this.specificationListProvider = specificationListProvider;
    }

    @GetMapping(value = {"/", ""})
    public List<AnnouncementTransferObject> getAnnouncements() {
        return getFilteredAnnouncements("rentStatus!=BLOCKED");
    }

    @GetMapping(value = "/blocked")
    public List<AnnouncementTransferObject> getBlockedAnnouncements() {
        return getFilteredAnnouncements("rentStatus=BLOCKED");
    }

    @GetMapping(value = "/user/{id}")
    public List<AnnouncementTransferObject> getUserAnnouncements(@PathVariable("id") Long userId) {
        List<Announcement> announcements = announcementRepository.getAllByAuthor(userId);
        List<AnnouncementTransferObject> announcementTransferObjects = new ArrayList<>();
        for (Announcement announcement: announcements)
            announcementTransferObjects.add(new AnnouncementTransferObject(announcement));
        return announcementTransferObjects;
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
        LinkedList<Specification<AnnouncementDetails>> specificationList =
                (LinkedList<Specification<AnnouncementDetails>>) specificationListProvider.getSpecificationListWithCompressedCriteria(criteria);

        Specification<AnnouncementDetails> specification = specificationList.pop();
        for (Specification<AnnouncementDetails> spec: specificationList)
            specification = specification.and(spec);

        for (AnnouncementDetails announcementDetails: announcementDetailsRepository.findAll(specification))
            announcementTransferObjects.add(new AnnouncementTransferObject(announcementDetails.getAnnouncement()));

        return announcementTransferObjects;
    }
}
