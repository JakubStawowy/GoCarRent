package com.example.gocarrentspringbootapplication.data.dto;

import com.example.gocarrentspringbootapplication.data.po.Feedback;

import java.io.Serializable;

public class FeedbackTransferObject implements Serializable {

    private final String content;
    private final String createdAt;
    private final int rate;
    private final String authorName;
    private final String authorSurname;
    private final Long authorId;

    public FeedbackTransferObject(Feedback feedback) {
        content = feedback.getContent();
        createdAt = feedback.getCreatedAt().toString();
        rate = feedback.getRate().ordinal();
        authorName = feedback.getAuthor().getUserDetails().getName();
        authorSurname = feedback.getAuthor().getUserDetails().getSurname();
        authorId = feedback.getAuthor().getId();
    }

    public FeedbackTransferObject(String content, String createdAt, int rate, String authorName, String authorSurname, Long authorId) {
        this.content = content;
        this.createdAt = createdAt;
        this.rate = rate;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getRate() {
        return rate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
