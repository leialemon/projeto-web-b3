package tech.ada.jjh.homebroker.util;

import java.math.BigDecimal;

public interface FeeCalculationRule{
    BigDecimal calculate(BigDecimal orderPrice, Double feeAmount);

}
