package tech.ada.jjh.homebroker.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AppUserDTOResponse {
    private String cpf;
    private String name;
    private String email;
    private BigDecimal balance;
    private List<TransactionDTOResponse> transactionHistory;
    private List<OrderDTOResponse> orderHistory;
    private Map<String, Integer> portfolio;

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Map<String, Integer> portfolio) {
        this.portfolio = portfolio;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionDTOResponse> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionDTOResponse> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public List<OrderDTOResponse> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderDTOResponse> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
