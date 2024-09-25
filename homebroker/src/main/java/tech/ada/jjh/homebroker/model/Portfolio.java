package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Setter @Getter
public class Portfolio{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    private AppUser appUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Stock> stocks;

    Map<String, Map<String, Integer>> outerMap = new HashMap<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Broker broker;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderHistory;
}
