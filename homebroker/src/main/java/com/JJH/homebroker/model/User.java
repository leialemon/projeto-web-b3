package com.JJH.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "user_name", nullable = false)
    private String name;

    // Verificar com regex?
    @Column(name = "user_mail", nullable = false)
    private String mail;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_token", nullable = false)
    private String token;

    @Column(name = "user_birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "user_balance", precision = 16, scale = 4, nullable = false)
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Portfolio> portfolios;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionHistory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderHistory;

}
