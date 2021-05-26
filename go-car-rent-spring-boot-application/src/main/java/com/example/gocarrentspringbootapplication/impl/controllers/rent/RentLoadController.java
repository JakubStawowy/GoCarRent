package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.RentTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/rents")
public class RentLoadController {

    private final RentRepository rentRepository;

    @Autowired
    public RentLoadController(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @GetMapping(value = "/tenant/{id}")
    public List<RentTransferObject> getTenantRents(@PathVariable("id") Long tenantId) {
        List<Rent> rents = rentRepository.getAllByTenant(tenantId);
        List<RentTransferObject> rentTransferObjects = new ArrayList<>();
        for (Rent rent: rents)
            rentTransferObjects.add(new RentTransferObject(
                    new AnnouncementTransferObject(rent.getAnnouncement()),
                    new BigDecimal("100"),
                    rent.getRentedAt(),
                    rent.getRentedAt()
            ));
        return rentTransferObjects;
    }
}
