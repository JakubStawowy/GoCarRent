package com.example.gocarrentspringbootapplication.finance.controllers;

import com.example.gocarrentspringbootapplication.finance.dao.RentRepository;
import com.example.gocarrentspringbootapplication.finance.api.IRentPropertiesManager;
import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.finance.dto.RentTransferObject;
import com.example.gocarrentspringbootapplication.finance.po.Rent;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(value = RentManageController.BASE_ENDPOINT)
public class RentManageController {

    public static final String BASE_ENDPOINT = "/api/rents";
    private final RentRepository rentRepository;
    private final IRentPropertiesManager rentPropertiesManager;

    @Autowired
    public RentManageController(RentRepository rentRepository, IRentPropertiesManager rentPropertiesManager) {
        this.rentRepository = rentRepository;
        this.rentPropertiesManager = rentPropertiesManager;
    }

    @GetMapping(value = EndpointRepository.TENANT_RENTS_ENDPOINT)
    public List<RentTransferObject> getTenantRents(@PathVariable("id") Long tenantId) {
        List<Rent> rents = rentRepository.getAllByTenant(tenantId);
        List<RentTransferObject> rentTransferObjects = new ArrayList<>();
        for (Rent rent: rents)
            rentTransferObjects.add(new RentTransferObject(
                    rent.getId(),
                    new AnnouncementTransferObject(rent.getAnnouncement()),
                    rentPropertiesManager.getFee(rent),
                    rent.getRentedAt(),
                    rentPropertiesManager.getRentTime(rent),
                    rent.getRentStatus()
            ));
        return rentTransferObjects;
    }

    @DeleteMapping(value = EndpointRepository.DELETE_RENT_ENDPOINT)
    public void deleteRent(@PathVariable("rentId") Long rentId) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        optionalRent.ifPresent(rentRepository::delete);
    }
}
