package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.data.po.Announcement;
import com.example.gocarrentspringbootapplication.data.po.AnnouncementDetails;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

public class TestAnnouncementProvider {

    public final static String TITLE = "Test";
    public final static Long ID = (long) 1;
    public final static String AMOUNT = "100";
    public final static String CURRENCY = "PLN";
    public final static String CAR_BRAND = "Brand";
    public final static String CAR_MODEL = "Model";
    public final static String TIME_UNIT = "HOURS";

    public static Announcement getTestAnnouncement() {
        Announcement announcement = new Announcement();
        AnnouncementDetails announcementDetails = new AnnouncementDetails();
        announcementDetails.setId(ID);
        announcementDetails.setTitle(TITLE);
        announcementDetails.setAmount(new BigDecimal(AMOUNT));
        announcementDetails.setCurrency(Currency.getInstance(CURRENCY));
        announcementDetails.setCarBrand(CAR_BRAND);
        announcementDetails.setCarModel(CAR_MODEL);
        announcementDetails.setTimeUnit(TimeUnit.of(ChronoUnit.valueOf(TIME_UNIT)));
        announcement.setAnnouncementDetails(announcementDetails);
        announcement.setId(ID);
        announcement.setAuthor(TestUserProvider.getTestUser());
        return announcement;
    }
}
