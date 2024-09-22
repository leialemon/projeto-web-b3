package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Getter @Setter
@Entity
@RequestMapping("api/v1/brokers")
public class Broker{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "broker_id", nullable = false)
    private Long id;

    @Column(name = "broker_name", nullable = false)
    private String name;

    /*@Column(name = "broker_fees")         Talvez seja necessária outra classe.
    private Map<String, brokerFee> fee;*/

}
