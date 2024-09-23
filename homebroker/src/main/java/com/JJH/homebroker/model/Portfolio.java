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

    @Column(name = "portfolio_broker", nullable = false)
    private Broker broker;

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Stock> stocks;

}
