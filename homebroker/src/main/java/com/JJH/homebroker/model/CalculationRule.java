package com.JJH.homebroker.model;

import java.math.BigDecimal;

public interface CalculationRule {
    BigDecimal calculate(Double feeAmount, BigDecimal orderAmount);
}
