package tech.ada.jjh.homebroker.model;

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
