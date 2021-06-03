package com.example.gocarrentspringbootapplication.data.api;

import com.example.gocarrentspringbootapplication.data.po.Announcement;

public interface IAnnouncementBuilder extends Builder<Announcement> {
    IAnnouncementBuilder setTitle(String title);
    IAnnouncementBuilder setAmount(String amount);
    IAnnouncementBuilder setCurrency(String currency);
    IAnnouncementBuilder setCarBrand(String brand);
    IAnnouncementBuilder setCarModel(String model);
    IAnnouncementBuilder setTimeUnit(String timeUnit);
    IAnnouncementBuilder setAuthor(Long authorId);
}