package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "app_user")
public class AppUser {

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

    @Column(name = "user_birth", nullable = false)
    private String birthDate;

    @Column(name = "user_balance")
    private BigDecimal balance;

    @ElementCollection
    @CollectionTable(name = "user_portfolio", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyJoinColumn(name = "stock_id")
    @Column(name = "quantity")
    private Map<Stock, Integer> portfolio;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionHistory;

    @OneToMany(mappedBy = "user")
    private List<Order> orderHistory;

    public Map<Stock, Integer> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Map<Stock, Integer> portfolio) {
        this.portfolio = portfolio;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Portfolio> portfolios;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

//    public List<Portfolio> getPortfolios() {
//        return portfolios;
//    }

//    public void setPortfolios(List<Portfolio> portfolios) {
//        this.portfolios = portfolios;
//    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

}
