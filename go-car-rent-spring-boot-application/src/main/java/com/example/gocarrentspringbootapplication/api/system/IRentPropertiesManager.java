package com.example.gocarrentspringbootapplication.api.system;

import com.example.gocarrentspringbootapplication.impl.models.Rent;

import java.math.BigDecimal;

public interface IRentPropertiesManager {
    BigDecimal getFee(Rent rent);
    BigDecimal getRentTime(Rent rent);
}
