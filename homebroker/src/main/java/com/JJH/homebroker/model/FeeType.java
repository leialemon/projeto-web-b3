package com.JJH.homebroker.model;

import lombok.Getter;

@Getter
public enum FeeType {
    CUSTODY_TAX,
    BROKERAGE,
    EMOLUMENT,
    LIQUIDATION_FEE,
    INCOME_TAX;

    private CalculationRules calculationRules;

    FeeType(CalculationRules calculationRules){
        this.calculationRules = calculationRules;

    }

    //TODO: criar regras de cálculo e passar no construtor do enum.
}
