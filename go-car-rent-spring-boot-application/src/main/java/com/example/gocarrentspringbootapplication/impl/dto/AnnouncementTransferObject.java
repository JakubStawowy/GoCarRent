package com.example.gocarrentspringbootapplication.impl.dto;

import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;

import java.io.Serializable;

public class AnnouncementTransferObject implements Serializable {

    private Long id;
    private final String title;
    private final String amount;
    private final String currency;
    private final String timeUnit;
    private final String carBrand;
    private final String carModel;
    private final Long authorId;
    private final AnnouncementStatus status;

    public AnnouncementTransferObject(String title, String amount, String currency, String timeUnit, String carBrand, String carModel, Long authorId, AnnouncementStatus status) {
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.timeUnit = timeUnit;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.authorId = authorId;
        this.status = status;
    }

    public AnnouncementTransferObject(Announcement announcement) {
        id = announcement.getId();
        title = announcement.getAnnouncementDetails().getTitle();
        amount = announcement.getAnnouncementDetails().getAmount().toPlainString();
        currency = announcement.getAnnouncementDetails().getCurrency().getCurrencyCode();
        timeUnit = announcement.getAnnouncementDetails().getTimeUnit().toString();
        carBrand = announcement.getAnnouncementDetails().getCarBrand();
        carModel = announcement.getAnnouncementDetails().getCarModel();
        authorId = announcement.getAuthor().getId();
        status = announcement.getAnnouncementDetails().getRentStatus();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public AnnouncementStatus getStatus() {
        return status;
    }
}
