package com.JJH.homebroker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Stock {

    @Id
    @Column
    private String ticker;

    @Column
    private String name;

    //TODO: manter como bigdecimal ou mudar pra double?
    @Column
    private BigDecimal price;
}
