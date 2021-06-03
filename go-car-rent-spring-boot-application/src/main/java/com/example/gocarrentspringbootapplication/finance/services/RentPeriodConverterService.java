package com.example.gocarrentspringbootapplication.finance.services;

import com.example.gocarrentspringbootapplication.finance.api.IPeriodConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
public final class RentPeriodConverterService implements IPeriodConverter {
    @Override
    public BigDecimal getNumberOfHoursFromPeriod(final long period) {
        return new BigDecimal(TimeUnit.MILLISECONDS.toHours(period));
    }

    @Override
    public BigDecimal getNumberOfDaysFromPeriod(final long period) {
        return new BigDecimal(TimeUnit.MILLISECONDS.toDays(period));
    }
}
