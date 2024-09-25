package tech.ada.jjh.homebroker.model;

public enum FeeType{
    BROKERAGE_PERCENTILE(new FeePercentileCalculationRule()),
    BROKERAGE_FIXED(new FeeFixedCalculationRule()),
    CUSTODY_PERCENTILE(new FeePercentileCalculationRule()),
    CUSTODY_FIXED(new FeeFixedCalculationRule()),
    TAX(new FeePercentileCalculationRule()),
    EMOLUMENT_NEGOTIATION(new FeePercentileCalculationRule()),
    EMOLUMENT_LIQUIDATION(new FeePercentileCalculationRule())
    ;
    private FeeCalculationRule calculationRule;

    FeeType(FeeCalculationRule calculationRule){
        this.calculationRule = calculationRule;
    }

    public FeeCalculationRule getCalculationRule(){
        return this.calculationRule;
    }
}
