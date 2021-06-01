package com.example.gocarrentspringbootapplication.impl.dto;

import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;
import com.example.gocarrentspringbootapplication.impl.models.Message;

import java.io.Serializable;

public class MessageTransferObject implements Serializable {

    private final Long messageId;
    private final RentMessageType rentMessageType;
    private final Long authorId;
    private final Long tenantId;
    private final Long announcementId;
    private final Boolean flag;
    private Long rentId;

    public MessageTransferObject(Long messageId, RentMessageType rentMessageType, Long authorId, Long tenantId, Long announcementId, Boolean flag) {
        this.messageId = messageId;
        this.rentMessageType = rentMessageType;
        this.authorId = authorId;
        this.tenantId = tenantId;
        this.announcementId = announcementId;
        this.flag = flag;
    }

    public MessageTransferObject(Message message) {
        messageId = message.getId();
        rentMessageType = message.getRentMessageType();
        authorId = message.getAuthor().getId();
        tenantId = message.getTenant().getId();
        announcementId = message.getAnnouncement().getId();
        flag = message.isFlag();
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

    public Long getTenantId() {
        return tenantId;
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
}


