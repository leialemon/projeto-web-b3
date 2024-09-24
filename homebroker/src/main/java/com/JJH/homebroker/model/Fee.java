package com.JJH.homebroker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fee{
    private CalculationRule calculationRule;
    private String name;
    private Double amount;
}
