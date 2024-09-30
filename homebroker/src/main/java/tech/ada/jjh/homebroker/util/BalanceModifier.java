package tech.ada.jjh.homebroker.util;

import java.math.BigDecimal;

public interface BalanceModifier {
    BigDecimal calculate(BigDecimal balance, BigDecimal modifierAmount);
}
