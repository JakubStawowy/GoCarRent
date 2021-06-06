package com.example.gocarrentspringbootapplication.finance.po;

import com.example.gocarrentspringbootapplication.data.po.Announcement;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.finance.enums.RentStatus;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "rent_status")
    private RentStatus rentStatus;

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
        rentStatus = RentStatus.ON_GOING;
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

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }
}
