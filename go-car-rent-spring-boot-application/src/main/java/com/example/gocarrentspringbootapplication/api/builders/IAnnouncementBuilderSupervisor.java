package com.example.gocarrentspringbootapplication.api.builders;

import com.example.gocarrentspringbootapplication.impl.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;

public interface IAnnouncementBuilderSupervisor {
    Announcement construct(AnnouncementTransferObject transferObject, IAnnouncementBuilder announcementBuilder);
}
