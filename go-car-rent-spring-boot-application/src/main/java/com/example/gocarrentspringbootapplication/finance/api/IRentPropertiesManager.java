package com.example.gocarrentspringbootapplication.finance.api;

import com.example.gocarrentspringbootapplication.finance.po.Rent;

import java.math.BigDecimal;

public interface IRentPropertiesManager {
    BigDecimal getFee(Rent rent);
    BigDecimal getRentTime(Rent rent);
}
