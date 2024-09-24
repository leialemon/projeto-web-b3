package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Setter
@Getter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Broker broker;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderHistory;
}
