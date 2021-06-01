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

        BigDecimal period;

        if ((period = getRentTime(rent)) != null)
            return rent.getAnnouncement().getAnnouncementDetails().getAmount().multiply(period.add(new BigDecimal("1")));

        return null;
    }

    @Nullable
    @Override
    public BigDecimal getRentTime(Rent rent) {

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
