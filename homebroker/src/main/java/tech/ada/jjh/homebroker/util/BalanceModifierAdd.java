package tech.ada.jjh.homebroker.util;

import java.math.BigDecimal;

public class BalanceModifierAdd implements BalanceModifier{
    @Override
    public BigDecimal calculate(BigDecimal balance, BigDecimal modifierAmount) {
        return balance.add(modifierAmount);
    }
}
