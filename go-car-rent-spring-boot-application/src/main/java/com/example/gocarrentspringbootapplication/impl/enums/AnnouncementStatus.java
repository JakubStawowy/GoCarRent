package com.example.gocarrentspringbootapplication.impl.enums;

public enum AnnouncementStatus {
    FREE,
    RENTED,
    RETURNED,
    BLOCKED;
    public static AnnouncementStatus get(String status) {
        switch (status) {
            case "FREE": return AnnouncementStatus.FREE;
            case "RENTED": return AnnouncementStatus.RENTED;
            case "RETURNED": return AnnouncementStatus.RETURNED;
            case "BLOCKED": return AnnouncementStatus.BLOCKED;
            default: return null;
        }
    }
}
