package com.example.gocarrentspringbootapplication.impl.system;

import com.example.gocarrentspringbootapplication.api.system.IRentPropertiesManager;
import com.example.gocarrentspringbootapplication.api.system.IPeriodConverter;
import com.example.gocarrentspringbootapplication.impl.models.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public final class RentPropertiesManager implements IRentPropertiesManager {

    private final IPeriodConverter periodConverter;

    @Autowired
    public RentPropertiesManager(IPeriodConverter periodConverter) {
        this.periodConverter = periodConverter;
    }

    @Nullable
    @Override
    public BigDecimal getFee(Rent rent) {

        BigDecimal amount = rent.getAnnouncement().getAnnouncementDetails().getAmount();
        BigDecimal period;

        if ((period = getRentDays(rent)) != null)
            return amount.multiply(period);

        return null;
    }

    @Nullable
    @Override
    public BigDecimal getRentDays(Rent rent) {

        TimeUnit timeUnit = rent.getAnnouncement().getAnnouncementDetails().getTimeUnit();
        long period = periodConverter.getPeriod(rent.getRentedAt(), rent.getReturnedAt());

        if (timeUnit.equals(TimeUnit.HOURS))
            return (BigDecimal) periodConverter.getNumberOfHoursFromPeriod(period);

        if (timeUnit.equals(TimeUnit.DAYS))
            return (BigDecimal) periodConverter.getNumberOfDaysFromPeriod(period);

        return null;
    }
}
