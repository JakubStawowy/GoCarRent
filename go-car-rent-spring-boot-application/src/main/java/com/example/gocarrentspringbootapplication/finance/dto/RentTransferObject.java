package com.example.gocarrentspringbootapplication.finance.dto;

import com.example.gocarrentspringbootapplication.data.dto.AnnouncementTransferObject;
import com.example.gocarrentspringbootapplication.finance.enums.RentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class RentTransferObject implements Serializable {

    private final Long rentId;
    private final AnnouncementTransferObject announcement;
    private final BigDecimal fee;
    private final Timestamp rentedAt;
    private final BigDecimal rentTime;
    private final RentStatus rentStatus;

    public RentTransferObject(Long rentId, AnnouncementTransferObject announcement, BigDecimal fee, Timestamp rentedAt, BigDecimal rentTime, RentStatus rentStatus) {
        this.rentId = rentId;
        this.announcement = announcement;
        this.fee = fee;
        this.rentedAt = rentedAt;
        this.rentTime = rentTime;
        this.rentStatus = rentStatus;
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

    public RentStatus getRentStatus() {
        return rentStatus;
    }
}
