package com.example.gocarrentspringbootapplication.impl.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RentTransferObject {

    private final AnnouncementTransferObject announcement;
    private final BigDecimal fee;
    private final Timestamp rentedAt;
    private final Timestamp rentTime;

    public RentTransferObject(AnnouncementTransferObject announcement, BigDecimal fee, Timestamp rentedAt, Timestamp rentTime) {
        this.announcement = announcement;
        this.fee = fee;
        this.rentedAt = rentedAt;
        this.rentTime = rentTime;
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

    public Timestamp getRentTime() {
        return rentTime;
    }
}
