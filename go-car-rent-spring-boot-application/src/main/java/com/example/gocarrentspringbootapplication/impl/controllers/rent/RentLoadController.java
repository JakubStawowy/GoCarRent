package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.api.system.IPeriodConverter;
import com.example.gocarrentspringbootapplication.api.system.IRentPropertiesManager;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.RentTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/rents")
public class RentLoadController {

    private final RentRepository rentRepository;
    private final IRentPropertiesManager rentPropertiesManager;

    @Autowired
    public RentLoadController(RentRepository rentRepository, IRentPropertiesManager rentPropertiesManager) {
        this.rentRepository = rentRepository;
        this.rentPropertiesManager = rentPropertiesManager;
    }

    @GetMapping(value = "/tenant/{id}")
    public List<RentTransferObject> getTenantRents(@PathVariable("id") Long tenantId) {
        List<Rent> rents = rentRepository.getAllByTenant(tenantId);
        List<RentTransferObject> rentTransferObjects = new ArrayList<>();
        for (Rent rent: rents)
            rentTransferObjects.add(new RentTransferObject(
                    new AnnouncementTransferObject(rent.getAnnouncement()),
                    rentPropertiesManager.getFee(rent),
                    rent.getRentedAt(),
                    rentPropertiesManager.getRentDays(rent)
            ));
        return rentTransferObjects;
    }

}
