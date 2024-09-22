package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@RequestMapping("api/v1/transactions")
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tramsaction_id", nullable = false)
    private long id;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date_time", nullable = false)
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_user", nullable = false)
    private User user;

}
