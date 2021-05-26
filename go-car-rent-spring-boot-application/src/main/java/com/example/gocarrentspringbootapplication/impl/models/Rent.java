package com.example.gocarrentspringbootapplication.impl.models;

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
}