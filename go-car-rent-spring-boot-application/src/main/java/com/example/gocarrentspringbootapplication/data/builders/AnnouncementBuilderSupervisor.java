package com.example.gocarrentspringbootapplication.data.builders;

import com.example.gocarrentspringbootapplication.data.api.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.data.api.IAnnouncementBuilderSupervisor;
import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.data.po.Announcement;
import org.springframework.stereotype.Service;

@Service
public final class AnnouncementBuilderSupervisor implements IAnnouncementBuilderSupervisor {

    @Override
    public Announcement construct(final AnnouncementTransferObject announcementTransferObject, final IAnnouncementBuilder announcementBuilder) {
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
