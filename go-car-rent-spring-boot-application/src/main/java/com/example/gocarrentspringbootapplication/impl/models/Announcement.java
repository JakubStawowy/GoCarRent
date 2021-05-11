package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.impl.components.RentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rent_status")
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

    @NotNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "announcement_details_id")
    private AnnouncementDetails announcementDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "rent")
    private Set<User> tenants;

    @PrePersist
    public void set() {
        createdAt = new Timestamp(System.currentTimeMillis());
        rentStatus = RentStatus.FREE;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnnouncementDetails getAnnouncementDetails() {
        return announcementDetails;
    }

    public void setAnnouncementDetails(AnnouncementDetails announcementDetails) {
        this.announcementDetails = announcementDetails;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Set<User> getTenants() {
        return tenants;
    }

    public void setTenants(Set<User> tenants) {
        this.tenants = tenants;
    }
}
