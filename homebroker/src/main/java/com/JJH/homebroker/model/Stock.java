package com.JJH.homebroker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Getter @Setter
@Entity
@RequestMapping("api/v1/stocks")
public class Stock{
    @Id
    @Column(name = "stock_id", nullable = false)
    private String ticker;

    @Column(name = "stock_name")
    private String name;

    @Column(name = "stock_price")
    private BigDecimal price;
}
