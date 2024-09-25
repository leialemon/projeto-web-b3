package tech.ada.jjh.homebroker.model;

import java.math.BigDecimal;

//Retorna o valor da taxa a ser adicionado;
public class FeePercentileCalculationRule implements FeeCalculationRule{
    @Override
    public BigDecimal calculate(BigDecimal orderPrice, Double feeAmount) {
        return orderPrice.multiply(BigDecimal.valueOf((feeAmount/100)));
    }
}
