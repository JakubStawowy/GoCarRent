package com.example.gocarrentspringbootapplication.data.dto;

import com.example.gocarrentspringbootapplication.data.enums.AnnouncementStatus;
import com.example.gocarrentspringbootapplication.data.po.Announcement;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnnouncementTransferObject implements Serializable {

    private Long id;
    private Timestamp createdAt;
    private final String title;
    private final String amount;
    private final String currency;
    private final String timeUnit;
    private final String carBrand;
    private final String carModel;
    private final Long authorId;
    private final AnnouncementStatus status;
    private final String image;

    public AnnouncementTransferObject(String title, String amount, String currency, String timeUnit, String carBrand, String carModel, Long authorId, AnnouncementStatus status, String image) {
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.timeUnit = timeUnit;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.authorId = authorId;
        this.status = status;
        this.image = image;
    }

    public AnnouncementTransferObject(Announcement announcement) {
        id = announcement.getId();
        createdAt = announcement.getAnnouncementDetails().getCreatedAt();
        title = announcement.getAnnouncementDetails().getTitle();
        amount = announcement.getAnnouncementDetails().getAmount().toPlainString();
        currency = announcement.getAnnouncementDetails().getCurrency().getCurrencyCode();
        timeUnit = announcement.getAnnouncementDetails().getTimeUnit().toString();
        carBrand = announcement.getAnnouncementDetails().getCarBrand();
        carModel = announcement.getAnnouncementDetails().getCarModel();
        authorId = announcement.getAuthor().getId();
        status = announcement.getAnnouncementDetails().getRentStatus();
        image = announcement.getImage();
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getImage() {
        return image;
    }
}
