package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Broker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "broker_id", nullable = false)
    private Long id;

    @Column(name = "broker_name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<Fee> fees;

    //TODO métodos para checar incidência de taxas.
}
