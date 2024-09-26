package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class AppUser {

    //fazer isso no service
    public AppUser(){
        setBalance(BigDecimal.valueOf(0.00));
        setOrderHistory(new ArrayList<>());
        setPortfolios(new ArrayList<>());
        setTransactionHistory(new ArrayList<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    //TODO criar regra dos 18 anos no service e lançar exceção
    @Column(name = "user_birth", nullable = false)
    private String birthDate;

    @Column(name = "user_balance")
    private BigDecimal balance;

    //TODO retirar do DTO
    //pode ter um DTO de entrada e saída diferentes

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionHistory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @OneToMany
    private List<Order> orderHistory;

    //TODO mover para service
    public List<Order> getOrderHistory(){
        for (Portfolio p : this.getPortfolios()){
            this.orderHistory.addAll(p.getOrderHistory());
        }
        this.orderHistory.sort(null);
        return this.orderHistory;
    }
}
