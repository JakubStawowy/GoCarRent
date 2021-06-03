package com.example.gocarrentspringbootapplication.data.api;

import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.data.po.Announcement;

public interface IAnnouncementBuilderSupervisor {
    Announcement construct(AnnouncementTransferObject transferObject, IAnnouncementBuilder announcementBuilder);
}
