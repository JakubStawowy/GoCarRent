package com.example.gocarrentspringbootapplication.impl.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    Announcement announcement;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    User tenant;

    @NotNull
    @Column(name = "rented_at")
    private Timestamp rentedAt;

    @Nullable
    @Column(name = "returned_at")
    private Timestamp returnedAt;

    public Rent(Announcement announcement, User tenant) {
        this.announcement = announcement;
        this.tenant = tenant;
    }

    public Rent() {
    }

    @PrePersist
    public void setInitial() {
        rentedAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public Timestamp getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(Timestamp rentedAt) {
        this.rentedAt = rentedAt;
    }

    @Nullable
    public Timestamp getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(@Nullable Timestamp returnedAt) {
        this.returnedAt = returnedAt;
    }
}
