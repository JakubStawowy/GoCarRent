package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.impl.enums.AnnouncementStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "announcement_details")
public class AnnouncementDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

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

    @NotNull
    @Column(name = "rent_status")
    @Enumerated(EnumType.STRING)
    private AnnouncementStatus rentStatus;

    @NotNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(mappedBy = "announcementDetails")
    private Announcement announcement;

    public AnnouncementDetails() {
    }

    @PrePersist
    public void set() {
        createdAt = new Timestamp(System.currentTimeMillis());
        rentStatus = AnnouncementStatus.FREE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public AnnouncementStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(AnnouncementStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
