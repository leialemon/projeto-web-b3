package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_price", nullable = false)
    @Getter(AccessLevel.NONE)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Instant orderDateTime;

    @Column(name = "order_executed_price") // Preço de StopLoss e afins?
    private BigDecimal executedPrice;

    @Column(name = "order_executed_date_time")
    private LocalDateTime executedDateTime;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_stock_qt", nullable = false)
    private int stockQt;

    @Column(name = "order_stock", nullable = false)
    private Stock stock;

//    public BigDecimal calculateTotal(){
//        return this.price = stock.getPrice().multiply(BigDecimal.valueOf(this.stockQt));
//        // Usar método da classe Broker para checar se há incidência de Fee;
//        //Se houver incidência de fee:
//        //CalculationRule calculateFee = this.portfolio.getBroker().getFee().getCalculationRule();
//        //return calculateFee(this.portfolio.getBroker().getFee().getAmount(), this.price);
//    }
//
//    public BigDecimal getPrice(){
//        return calculateTotal();
//    }
}
