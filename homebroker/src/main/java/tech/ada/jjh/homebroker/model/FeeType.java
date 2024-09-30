package tech.ada.jjh.homebroker.model;

import tech.ada.jjh.homebroker.util.FeeCalculationRule;
import tech.ada.jjh.homebroker.util.FeeFixedCalculationRule;
import tech.ada.jjh.homebroker.util.FeePercentileCalculationRule;

public enum FeeType{
    BROKERAGE_PERCENTILE(new FeePercentileCalculationRule()),
    BROKERAGE_FIXED(new FeeFixedCalculationRule()),
    CUSTODY_PERCENTILE(new FeePercentileCalculationRule()),
    CUSTODY_FIXED(new FeeFixedCalculationRule()),
    TAX(new FeePercentileCalculationRule()),
    EMOLUMENT(new FeePercentileCalculationRule()),
    ;
    private FeeCalculationRule calculationRule;

    FeeType(FeeCalculationRule calculationRule){
        this.calculationRule = calculationRule;
    }

    public FeeCalculationRule getCalculationRule(){
        return this.calculationRule;
    }
}
