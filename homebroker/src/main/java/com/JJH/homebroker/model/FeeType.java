package com.JJH.homebroker.model;

public enum FeeType {
    EMOLUMENT (new CalculationPercentile()),
    BROKERAGE_FIXED (new CalculationFixed()),
    BROKERAGE_PERCENTILE (new CalculationPercentile()),
    LIQUIDATION (new CalculationPercentile()),
    CUSTODY_FIXED (new CalculationFixed()),
    CUSTODY_PERCENTILE (new CalculationPercentile()),
    TAX (new CalculationPercentile());

    private CalculationRule calculationRule;

    FeeType(CalculationRule calculationRule){
        this.calculationRule = calculationRule;
    }

    public CalculationRule getCalculationRule(){
        return this.calculationRule;
    }
}
