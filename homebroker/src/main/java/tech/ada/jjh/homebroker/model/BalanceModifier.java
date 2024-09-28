package tech.ada.jjh.homebroker.model;

import java.math.BigDecimal;

public interface BalanceModifier {
    BigDecimal calculate(BigDecimal balance, BigDecimal modifierAmount);
}
