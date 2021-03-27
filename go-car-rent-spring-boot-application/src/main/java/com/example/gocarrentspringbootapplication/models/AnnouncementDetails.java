package com.example.gocarrentspringbootapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "announcement_details")
public class AnnouncementDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Currency currency;

    @NotNull
    @Column(name = "time_unit")
    @Enumerated(EnumType.STRING)
    private TimeUnit timeUnit;

    @NotNull
    @Column(name = "car_brand")
    private String carBrand;

    @NotNull
    @Column(name = "car_model")
    private String carModel;

    @OneToOne(mappedBy = "announcementDetails")
    private Announcement announcement;

    public AnnouncementDetails(@NotNull BigDecimal amount, @NotNull Currency currency, @NotNull TimeUnit timeUnit, @NotNull String carBrand, @NotNull String carModel) {
        this.amount = amount;
        this.currency = currency;
        this.timeUnit = timeUnit;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public AnnouncementDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }
}
