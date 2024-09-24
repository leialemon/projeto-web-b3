package com.JJH.homebroker.model;

import java.math.BigDecimal;

public class CalculationPercentile implements CalculationRule {
    @Override
    public BigDecimal calculate(Double feeAmount, BigDecimal orderAmount) {
        return orderAmount.multiply(BigDecimal.valueOf(feeAmount));
    }
}
