package com.example.gocarrentspringbootapplication.models;

import com.example.gocarrentspringbootapplication.components.RentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @Column(name = "rent_status")
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "announcement_details_id")
    private AnnouncementDetails announcementDetails;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @JsonIgnore
    @ManyToMany(mappedBy = "rent")
    private Set<User> tenants;

    public Announcement(@NotEmpty String title, @NotNull RentStatus rentStatus, AnnouncementDetails announcementDetails, User author) {
        this.title = title;
        this.rentStatus = rentStatus;
        this.announcementDetails = announcementDetails;
        this.author = author;
    }

    public Announcement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
