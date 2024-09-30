package tech.ada.jjh.homebroker.model;

import tech.ada.jjh.homebroker.util.*;

public enum OrderType {
    BUYING(new BalanceModifierSubtract(), new PortfolioModifierAddStock()),
    SELLING(new BalanceModifierAdd(), new PortfolioModifierRemoveStock());

    private final BalanceModifier balanceModifier;
    private final PortfolioModifier portfolioModifier;

    OrderType(BalanceModifier balanceModifier, PortfolioModifier portfolioModifier){
        this.balanceModifier = balanceModifier;
        this.portfolioModifier = portfolioModifier;
    }

    public BalanceModifier getBalanceModifier(){
        return this.balanceModifier;
    }
    public PortfolioModifier getPortfolioModifier(){
        return this.portfolioModifier;
    }
}