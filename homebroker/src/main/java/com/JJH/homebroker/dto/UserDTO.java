package com.JJH.homebroker.dto;

import com.JJH.homebroker.model.Order;
import com.JJH.homebroker.model.Portfolio;
import com.JJH.homebroker.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String cpf;
    private String name;
    private String mail;
    private String password;
    private LocalDate birthday;
    private BigDecimal balance;
    private List<Portfolio> portfolios;
    private List<Transaction> transactionHistory;
    private List<Order> orderHistory;
}
