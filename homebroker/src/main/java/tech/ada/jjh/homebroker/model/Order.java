package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @Column(name = "order_raw_price", precision = 16, scale = 4)
    private BigDecimal rawPrice;

    @Column(name = "order_total_price", precision = 16, scale = 4)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column
    private LocalDateTime dateTimeExecution;

    @OneToMany
    private List<Fee> fees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(BigDecimal rawPrice) {
        this.rawPrice = rawPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public LocalDateTime getDateTimeExecution() {
        return dateTimeExecution;
    }

    public void setDateTimeExecution(LocalDateTime dateTimeExecution) {
        this.dateTimeExecution = dateTimeExecution;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    //TODO colocar no service de criação da Order
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
