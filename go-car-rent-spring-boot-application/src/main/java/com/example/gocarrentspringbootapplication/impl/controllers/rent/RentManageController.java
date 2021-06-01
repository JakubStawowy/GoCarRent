package com.example.gocarrentspringbootapplication.impl.controllers.rent;

import com.example.gocarrentspringbootapplication.api.dao.repositories.RentRepository;
import com.example.gocarrentspringbootapplication.api.system.IRentPropertiesManager;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.MessageTransferObject;
import com.example.gocarrentspringbootapplication.impl.dto.RentTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import com.example.gocarrentspringbootapplication.impl.repositories.RabbitMessageQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/rents")
public class RentManageController {

    private final RentRepository rentRepository;
    private final IRentPropertiesManager rentPropertiesManager;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RentManageController(RentRepository rentRepository, IRentPropertiesManager rentPropertiesManager, RabbitTemplate rabbitTemplate) {
        this.rentRepository = rentRepository;
        this.rentPropertiesManager = rentPropertiesManager;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/tenant/{id}")
    public List<RentTransferObject> getTenantRents(@PathVariable("id") Long tenantId) {
        List<Rent> rents = rentRepository.getAllByTenant(tenantId);
        List<RentTransferObject> rentTransferObjects = new ArrayList<>();
        for (Rent rent: rents)
            rentTransferObjects.add(new RentTransferObject(
                    rent.getId(),
                    new AnnouncementTransferObject(rent.getAnnouncement()),
                    rentPropertiesManager.getFee(rent),
                    rent.getRentedAt(),
                    rentPropertiesManager.getRentTime(rent)
            ));
        return rentTransferObjects;
    }

    @GetMapping(value = "/messages")
    public List<MessageTransferObject> getRentMessages(@RequestParam Long userId) {

        List<MessageTransferObject> result = new LinkedList<>();
        MessageTransferObject buffer;

        while ((buffer = (MessageTransferObject) rabbitTemplate.receiveAndConvert(RabbitMessageQueues.QUEUE_TEMPLATE.replace(":uid", userId.toString()))) != null) {
            result.add(buffer);
        }

        return result;
    }

    @DeleteMapping(value = "/{rentId}/delete")
    public void deleteRent(@PathVariable("rentId") Long rentId) {
        Optional<Rent> optionalRent = rentRepository.findById(rentId);
        optionalRent.ifPresent(rentRepository::delete);
    }
}
