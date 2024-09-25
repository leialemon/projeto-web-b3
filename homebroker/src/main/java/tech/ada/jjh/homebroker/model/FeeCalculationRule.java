package tech.ada.jjh.homebroker.model;

import java.math.BigDecimal;

public interface FeeCalculationRule{
    BigDecimal calculate(BigDecimal orderPrice, Double feeAmount);

}
