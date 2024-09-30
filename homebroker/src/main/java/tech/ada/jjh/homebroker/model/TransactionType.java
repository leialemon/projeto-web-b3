package tech.ada.jjh.homebroker.model;

import tech.ada.jjh.homebroker.util.BalanceModifier;
import tech.ada.jjh.homebroker.util.BalanceModifierAdd;
import tech.ada.jjh.homebroker.util.BalanceModifierSubtract;

public enum TransactionType {
    DEPOSIT(new BalanceModifierAdd()),
    WITHDRAWAL(new BalanceModifierSubtract());

    private final BalanceModifier balanceModifier;

    TransactionType(BalanceModifier balanceModifier){
        this.balanceModifier = balanceModifier;
    }

    public BalanceModifier getBalanceModifier(){
        return this.balanceModifier;
    }
}
