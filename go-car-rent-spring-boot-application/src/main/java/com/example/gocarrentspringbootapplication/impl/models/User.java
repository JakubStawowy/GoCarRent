package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.impl.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @NotNull
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    private Boolean logged;

    @NotNull
    @JsonIgnore
    private String salt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messagesSent = new HashSet<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messagesReceived = new HashSet<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackSent = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackReceived = new HashSet<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Announcement> announcements = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "car_rent",
//            joinColumns = {@JoinColumn(name = "tenant_id")},
//            inverseJoinColumns = {@JoinColumn(name = "announcement_id")}
//            )
//    private Set<Announcement> rent;

    @OneToMany(mappedBy = "tenant")
    private Set<Rent> rents;

    public User(@NotEmpty String email, @NotEmpty String password, @NotNull UserRoles role, UserDetails userDetails) {
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
        this.role = role;
    }

    public User() {
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = new Date(System.currentTimeMillis());
        logged = false;
        role = UserRoles.ROLE_USER;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public UserRoles getRoles() {
        return role;
    }

    public void setRoles(UserRoles role) {
        this.role = role;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Set<Feedback> getFeedbackSent() {
        return feedbackSent;
    }

    public void setFeedbackSent(Set<Feedback> feedbackSent) {
        this.feedbackSent = feedbackSent;
    }

    public Set<Feedback> getFeedbackReceived() {
        return feedbackReceived;
    }

    public void setFeedbackReceived(Set<Feedback> feedbackReceived) {
        this.feedbackReceived = feedbackReceived;
    }

    public Set<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(Set<Message> sendedMessages) {
        this.messagesSent = sendedMessages;
    }

    public Set<Message> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(Set<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }
}
