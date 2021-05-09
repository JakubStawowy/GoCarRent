package com.example.gocarrentspringbootapplication.impl.dto;

import java.io.Serializable;

public class AnnouncementTransferObject implements Serializable {

    private final String title;
    private final String amount;
    private final String currency;
    private final String timeUnit;
    private final String carBrand;
    private final String carModel;
    private final Long authorId;

    public AnnouncementTransferObject(String title, String amount, String currency, String timeUnit, String carBrand, String carModel, Long authorId) {
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.timeUnit = timeUnit;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.authorId = authorId;
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
}
