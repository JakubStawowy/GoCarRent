package com.example.gocarrentspringbootapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Transient
    private String confirmedPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Log> logs;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messagesSent;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messagesReceived;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackSent;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackReceived;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Announcement> announcements;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_rent",
            joinColumns = {@JoinColumn(name = "tenant_id")},
            inverseJoinColumns = {@JoinColumn(name = "announcement_id")}
            )
    private Set<Announcement> rent;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
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


    public User() {
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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
