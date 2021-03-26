package com.example.gocarrentspringbootapplication.models;

import com.example.gocarrentspringbootapplication.components.RentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @Column(name = "rent_status")
    private RentStatus rentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "announcement_details_id")
    private AnnouncementDetails announcementDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "rent")
    private Set<User> tenants;

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
