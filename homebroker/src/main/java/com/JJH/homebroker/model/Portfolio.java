package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Getter @Setter
@Entity
@RequestMapping("api/v1/portfolios")
public class Portfolio{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolio_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "broker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private List<Stock> stocks;
}
