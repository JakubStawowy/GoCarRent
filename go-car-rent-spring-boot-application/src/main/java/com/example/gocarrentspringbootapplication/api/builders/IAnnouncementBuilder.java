package com.example.gocarrentspringbootapplication.api.builders;

import com.example.gocarrentspringbootapplication.impl.models.Announcement;

public interface IAnnouncementBuilder extends Builder<Announcement>{
    IAnnouncementBuilder setTitle(String title);
    IAnnouncementBuilder setAmount(String amount);
    IAnnouncementBuilder setCurrency(String currency);
    IAnnouncementBuilder setCarBrand(String brand);
    IAnnouncementBuilder setCarModel(String model);
    IAnnouncementBuilder setTimeUnit(String timeUnit);
    IAnnouncementBuilder setAuthor(Long authorId);

}
