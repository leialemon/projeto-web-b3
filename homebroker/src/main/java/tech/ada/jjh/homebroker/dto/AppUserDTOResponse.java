package tech.ada.jjh.homebroker.dto;


import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.model.Transaction;
import java.math.BigDecimal;
import java.util.List;

public class AppUserDTOResponse {
    private String cpf;
    private String name;
    private String email;
    private BigDecimal balance;
    private List<TransactionDTO> transactionHistory;
//    private List<Portfolio> portfolios;
    private List<Order> orderHistory;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionDTO> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionDTO> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

//    public List<Portfolio> getPortfolios() {
//        return portfolios;
//    }
//
//    public void setPortfolios(List<Portfolio> portfolios) {
//        this.portfolios = portfolios;
//    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
