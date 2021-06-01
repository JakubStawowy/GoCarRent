package com.example.gocarrentspringbootapplication.finance.services;

import com.example.gocarrentspringbootapplication.finance.api.IRentPropertiesManager;
import com.example.gocarrentspringbootapplication.finance.api.IPeriodConverter;
import com.example.gocarrentspringbootapplication.finance.po.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public final class RentPropertiesService implements IRentPropertiesManager {

    private final IPeriodConverter periodConverter;

    @Autowired
    public RentPropertiesService(IPeriodConverter periodConverter) {
        this.periodConverter = periodConverter;
    }

    @Nullable
    @Override
    public BigDecimal getFee(final Rent rent) {

        BigDecimal period;

        if ((period = getRentTime(rent)) != null)
            return rent.getAnnouncement().getAnnouncementDetails().getAmount().multiply(period);

        return null;
    }

    @Nullable
    @Override
    public BigDecimal getRentTime(final Rent rent) {

        TimeUnit timeUnit = rent.getAnnouncement().getAnnouncementDetails().getTimeUnit();
        long period = periodConverter.getPeriod(rent.getRentedAt(), rent.getReturnedAt());
        BigDecimal rentTime;

        if (timeUnit.equals(TimeUnit.HOURS)) {
            rentTime = (BigDecimal) periodConverter.getNumberOfHoursFromPeriod(period);
            return rentTime.add(new BigDecimal("1"));
        }

        if (timeUnit.equals(TimeUnit.DAYS)) {
            rentTime = (BigDecimal) periodConverter.getNumberOfDaysFromPeriod(period);
            return rentTime.add(new BigDecimal("1"));
        }

        return null;
    }
}
