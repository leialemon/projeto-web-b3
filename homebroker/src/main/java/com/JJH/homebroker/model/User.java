package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@RequestMapping("api/v1/users")
public class User{

    @Id
    @Column(name = " user_cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "user_name", nullable = false)
    private String name;

    // Verificar com regex?
    @Column(name = "user_mail", nullable = false)
    private String mail;

    // TODO: implementar criptografia
    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_token", nullable = false)
    private String token;

    @Column(name = "user_birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "user_balance", precision = 16, scale = 4, nullable = false)
    private BigDecimal balance;


    // Como trabalhar com um item que só garante unicidade através de duas chaves primárias?
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private List<Transaction> transactionHistory;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Order> orderHistory;

}
