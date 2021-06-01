package com.example.gocarrentspringbootapplication.finance.dto;

import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class RentTransferObject implements Serializable {

    private final Long rentId;
    private final AnnouncementTransferObject announcement;
    private final BigDecimal fee;
    private final Timestamp rentedAt;
    private final BigDecimal rentTime;

    public RentTransferObject(Long rentId, AnnouncementTransferObject announcement, BigDecimal fee, Timestamp rentedAt, BigDecimal rentTime) {
        this.rentId = rentId;
        this.announcement = announcement;
        this.fee = fee;
        this.rentedAt = rentedAt;
        this.rentTime = rentTime;
    }

    public Long getRentId() {
        return rentId;
    }

    public AnnouncementTransferObject getAnnouncement() {
        return announcement;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public Timestamp getRentedAt() {
        return rentedAt;
    }

    public BigDecimal getRentTime() {
        return rentTime;
    }
}
