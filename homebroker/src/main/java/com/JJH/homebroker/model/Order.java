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
    private OrderType orderType;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_stock_qt", nullable = false)
    private int stockQt;

    @Column(name = "order_stock", nullable = false)
    private Stock stock;

}
