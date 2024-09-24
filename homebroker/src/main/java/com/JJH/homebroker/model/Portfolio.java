package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Portfolio{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolio_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
