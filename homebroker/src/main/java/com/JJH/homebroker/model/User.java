package com.JJH.homebroker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @Column(length = 11)
    private String cpf;

    @Column
    private String name;

    // Verificar com regex?
    @Column
    private String email;

    // TODO: implementar criptografia
    @Column
    private String password;

    @Column(precision = 10, scale = 2)
    private BigDecimal balance;

    // TODO: implementar ligação com chave estrangeira
    private List<Portfolio> portfolios;
    private List<Transaction> transactionHistory;
    private List<Order> orderHistory;
}
