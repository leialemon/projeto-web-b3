package com.JJH.homebroker.model;

import java.math.BigDecimal;

public class CalculationFixed implements CalculationRule {
    @Override
    public BigDecimal calculate(Double feeAmount, BigDecimal orderAmount) {
        return orderAmount.add(BigDecimal.valueOf(feeAmount));
    }
}
