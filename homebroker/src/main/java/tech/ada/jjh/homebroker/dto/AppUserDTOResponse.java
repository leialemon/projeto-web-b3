package tech.ada.jjh.homebroker.dto;

import tech.ada.jjh.homebroker.model.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AppUserDTOResponse {
    private String cpf;
    private String name;
    private String email;
    private BigDecimal balance;
    private List<TransactionDTOResponse> transactionHistoryDTO;
//    private List<Portfolio> portfolios;
    private List<OrderDTOResponse> orderHistory;
    private Map<StockDTO, Integer> portfolio;

    public Map<StockDTO, Integer> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Map<StockDTO, Integer> portfolio) {
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

    public List<TransactionDTOResponse> getTransactionHistoryDTO() {
        return transactionHistoryDTO;
    }

    public void setTransactionHistoryDTO(List<TransactionDTOResponse> transactionHistoryDTO) {
        this.transactionHistoryDTO = transactionHistoryDTO;
    }

//    public List<Portfolio> getPortfolios() {
//        return portfolios;
//    }
//
//    public void setPortfolios(List<Portfolio> portfolios) {
//        this.portfolios = portfolios;
//    }

    public List<OrderDTOResponse> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderDTOResponse> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
