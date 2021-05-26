package com.example.gocarrentspringbootapplication.impl.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Set<User> getTenants() {
        return tenants;
    }

    public void setTenants(Set<User> tenants) {
        this.tenants = tenants;
    }
}
