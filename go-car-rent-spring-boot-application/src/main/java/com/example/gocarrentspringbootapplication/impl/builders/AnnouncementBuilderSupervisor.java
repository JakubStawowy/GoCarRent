package com.example.gocarrentspringbootapplication.impl.builders;

import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilderSupervisor;
import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import org.springframework.stereotype.Service;

@Service
public final class AnnouncementBuilderSupervisor implements IAnnouncementBuilderSupervisor {

    @Override
    public Announcement construct(AnnouncementTransferObject announcementTransferObject, IAnnouncementBuilder announcementBuilder) {
        return announcementBuilder
                .setTitle(announcementTransferObject.getTitle())
                .setAmount(announcementTransferObject.getAmount())
                .setCurrency(announcementTransferObject.getCurrency())
                .setTimeUnit(announcementTransferObject.getTimeUnit())
                .setCarBrand(announcementTransferObject.getCarBrand())
                .setCarModel(announcementTransferObject.getCarModel())
                .setAuthor(announcementTransferObject.getAuthorId())
                .build();
    }
}
