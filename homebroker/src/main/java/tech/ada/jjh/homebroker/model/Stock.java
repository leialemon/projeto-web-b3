package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter @Getter
public class Stock{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stock_ticker", nullable = false, unique = true)
    private String ticker;

    @Column
    private String name;

    @Column(name = "stock_price", precision = 16, scale = 2)
    private BigDecimal price;
}
