package com.example.gocarrentspringbootapplication.impl.enums;

public enum AnnouncementStatus {
    FREE,
    RENTED,
    RETURNED,
    BLOCKED;
    public static AnnouncementStatus get(String status) {
        if (status.equals(FREE.toString())) return AnnouncementStatus.FREE;
        if (status.equals(RENTED.toString())) return AnnouncementStatus.RENTED;
        if (status.equals(RETURNED.toString())) return AnnouncementStatus.RETURNED;
        if (status.equals(BLOCKED.toString())) return AnnouncementStatus.BLOCKED;
        if (status.equals(FREE.toString())) return AnnouncementStatus.FREE;
        return null;
    }
}
