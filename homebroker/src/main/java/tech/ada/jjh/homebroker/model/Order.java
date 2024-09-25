package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "stock_order")
public class Order implements Comparable<Order>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Stock stock;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(name = "order_price", precision = 16, scale = 4)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column
    private LocalDateTime dateTimeExecution;

    @OneToMany
    private List<Fee> fees;

    public void calculateTotal(){
        BigDecimal total = this.stock.getPrice().multiply(BigDecimal.valueOf(getStockQuantity()));
        //verificar a incidência de Fees, se houver, adicionar as fees da Broker e calcular;
        // total =  total.multiply(getPortfolio().getBroker().getFee);
        setPrice(total);
    }

    @Override
    public int compareTo(Order o) {
        int comparer = this.getDateTimeExecution().compareTo(o.getDateTimeExecution());
        if (comparer == 0){
            return this.getId().compareTo(o.getId());
        }
        return comparer;
    }

    //TODO perguntar ao professor se é uma boa prática embutir o calculateTotal no getPrice(); Perguntar também em relação ao getFees().
    //TODO reimplementar status e type;
}
