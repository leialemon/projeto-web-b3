package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Stock {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "stock_id", nullable = false)
        private Long id;

        @Column(name = "stock_ticker", nullable = false, unique = true)
        private String ticker;

        @Column(name = "stock_name")
        private String name;

        @Getter
        @Column(name = "stock_price", precision = 16, scale = 4, nullable = false)
        private BigDecimal price;

        @ManyToOne
        @JoinColumn(name = "portfolio_id")
        private Portfolio portfolio;
}
