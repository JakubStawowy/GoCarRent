package com.example.gocarrentspringbootapplication.impl.system;

import com.example.gocarrentspringbootapplication.api.system.IPeriodConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
public final class RentPeriodConverter implements IPeriodConverter {
    @Override
    public BigDecimal getNumberOfHoursFromPeriod(long period) {
        return new BigDecimal(TimeUnit.MILLISECONDS.toHours(period));
    }

    @Override
    public BigDecimal getNumberOfDaysFromPeriod(long period) {
        return new BigDecimal(TimeUnit.MILLISECONDS.toDays(period));
    }
}
