package com.example.gocarrentspringbootapplication.data.po;

import com.example.gocarrentspringbootapplication.data.enums.FeedbackRate;
import com.example.gocarrentspringbootapplication.repositories.TableNamesRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = TableNamesRepository.FEEDBACK_TABLE_NAME)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FeedbackRate rate;

    @NotNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Feedback(@NotEmpty String content, @NotNull FeedbackRate rate, User author, User user) {
        this.content = content;
        this.rate = rate;
        this.author = author;
        this.user = user;
    }

    public Feedback() {
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedbackRate getRate() {
        return rate;
    }

    public void setRate(FeedbackRate rate) {
        this.rate = rate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
