package com.example.gocarrentspringbootapplication.amqp.dto;

import com.example.gocarrentspringbootapplication.amqp.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.amqp.po.Message;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageTransferObject implements Serializable {

    private final Long messageId;
    private final RentMessageType rentMessageType;
    private final Long authorId;
    private final Long receiverId;
    private final Long announcementId;
    private final Boolean flag;
    private final Timestamp sentAt;
    private Long rentId;

    public MessageTransferObject(Message message) {
        messageId = message.getId();
        rentMessageType = message.getRentMessageType();
        authorId = message.getAuthor().getId();
        receiverId = message.getReceiver().getId();
        announcementId = message.getAnnouncement().getId();
        flag = message.isFlag();
        sentAt = message.getSentAt();
    }

    public Long getMessageId() {
        return messageId;
    }

    public RentMessageType getRentMessageType() {
        return rentMessageType;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public Boolean getFlag() {
        return flag;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }
}


