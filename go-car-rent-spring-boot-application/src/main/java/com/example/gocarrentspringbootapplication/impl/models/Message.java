package com.example.gocarrentspringbootapplication.impl.models;


import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "sent_at")
    private Timestamp sentAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RentMessageType rentMessageType;

    @Nullable
    private Boolean flag;

    @NotNull
    private Boolean archived;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @Nullable
    private Long rentId;

    public Message(@NotNull RentMessageType rentMessageType, @Nullable Boolean flag, User author, User receiver, Announcement announcement) {
        this.rentMessageType = rentMessageType;
        this.flag = flag;
        this.author = author;
        this.receiver = receiver;
        this.announcement = announcement;
    }

    public Message() {
    }

    @PrePersist
    public void setSentAt() {
        sentAt = new Timestamp(System.currentTimeMillis());
        archived = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public RentMessageType getRentMessageType() {
        return rentMessageType;
    }

    public void setRentMessageType(RentMessageType rentMessageType) {
        this.rentMessageType = rentMessageType;
    }

    @Nullable
    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(@Nullable Boolean flag) {
        this.flag = flag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Nullable
    public Long getRentId() {
        return rentId;
    }

    public void setRentId(@Nullable Long rentId) {
        this.rentId = rentId;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
