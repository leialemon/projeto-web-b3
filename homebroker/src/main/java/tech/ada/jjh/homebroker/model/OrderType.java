package tech.ada.jjh.homebroker.model;

public enum OrderType {
    BUYING(new BalanceModifierSubtract()),
    SELLING(new BalanceModifierAdd());

    private final BalanceModifier balanceModifier;

    OrderType(BalanceModifier balanceModifier){
        this.balanceModifier = balanceModifier;
    }

    public BalanceModifier getBalanceModifier(){
        return this.balanceModifier;
    }
}