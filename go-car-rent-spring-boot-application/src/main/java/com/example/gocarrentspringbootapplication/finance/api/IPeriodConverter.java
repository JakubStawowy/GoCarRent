package com.example.gocarrentspringbootapplication.finance.api;

import java.sql.Timestamp;

public interface IPeriodConverter {
    Number getNumberOfHoursFromPeriod(long period);
    Number getNumberOfDaysFromPeriod(long period);
    default long getPeriod(final Timestamp start, Timestamp end) {
        if (end == null)
            end = new Timestamp(System.currentTimeMillis());
        return end.getTime() - start.getTime();
    }
}
