package com.example.gocarrentspringbootapplication.impl.dto;

import com.example.gocarrentspringbootapplication.impl.enums.RentMessageType;

import java.io.Serializable;

public class RentMessageTransferObject implements Serializable {

    private final RentMessageType rentMessageType;
    private final Long authorId;
    private final Long tenantId;
    private final Long announcementId;
    private final Boolean flag;

    public RentMessageTransferObject(RentMessageType rentMessageType, Long authorId, Long tenantId, Long announcementId, Boolean flag) {
        this.rentMessageType = rentMessageType;
        this.authorId = authorId;
        this.tenantId = tenantId;
        this.announcementId = announcementId;
        this.flag = flag;
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
}


