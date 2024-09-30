package tech.ada.jjh.homebroker.util;

import java.math.BigDecimal;

public class BalanceModifierSubtract implements BalanceModifier{
    @Override
    public BigDecimal calculate(BigDecimal balance, BigDecimal modifierAmount) {
        return balance.subtract(modifierAmount);
    }
}
