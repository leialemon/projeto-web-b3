package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "stock_order")
public class Order implements Comparable<Order>{

    public Order(){
        this.fees = new ArrayList<>();
        Fee b3emolument = new Fee();
        b3emolument.setAmount(0.03);
        b3emolument.setName("b3emoluments");
        b3emolument.setType(FeeType.EMOLUMENT);
        this.fees.add(b3emolument);
        Fee taxes = new Fee();
        taxes.setName("taxes");
        taxes.setAmount(0.02);
        taxes.setType(FeeType.TAX);
        this.fees.add(taxes);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Stock stock;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(name = "order_price", precision = 16, scale = 4)
    private BigDecimal rawPrice;

    @Column(name = "order_price", precision = 16, scale = 4)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column
    private LocalDateTime dateTimeExecution;

    @OneToMany
    private List<Fee> fees;

    public void calculateRawPrice(){
        BigDecimal rawTotal = this.stock.getPrice().multiply(BigDecimal.valueOf(getStockQuantity()));
        setRawPrice(rawTotal);
    }

    public void calculateTotalPrice(){
        this.fees.addAll(getPortfolio().getBroker().getFees());
        BigDecimal rawTotal = getRawPrice();
        BigDecimal feeTotal = BigDecimal.ZERO;
        for (Fee fee : this.fees){
            feeTotal = feeTotal.add(fee.getType().getCalculationRule().calculate(rawTotal, fee.getAmount()));
        }
        rawTotal = rawTotal.add(feeTotal);
        setTotalPrice(rawTotal);
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
