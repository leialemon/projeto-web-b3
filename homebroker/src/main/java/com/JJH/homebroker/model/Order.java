package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Getter @Setter
@Entity
@RequestMapping("api/v1/orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;

    @Column(name = "order_price", nullable = false)
    private BigDecimal price;

    @Column(name = "order_executed_price") // Preço de StopLoss e afins?
    private BigDecimal executedPrice;

    @Column(name = "order_portfolio", nullable = false)
    private Portfolio portfolio;

    @Column(name = "order_type", nullable = false)
    private Enum

}
