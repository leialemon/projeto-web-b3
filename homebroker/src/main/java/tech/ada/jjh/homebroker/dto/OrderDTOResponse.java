package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tech.ada.jjh.homebroker.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderDTOResponse {

    private List<Fee> fees;

    @NotBlank(message = "É necessário incluir uma ação.")
    private Stock stock;

    @NotBlank(message = "É necessário incluir uma carteira.")
    private Portfolio portfolio;

    private LocalDateTime dateTimeExecution;

    @Size(min = 1, message = "É necessário possuir ações.")
    @NotBlank(message = "É necessário possuir ações.")
    private Integer stockQuantity;

    @Size(min = 1, message = "É necessário possuir o preço individual.")
    @NotBlank(message = "É necessário possuir o preço individual.")
    private BigDecimal rawPrice;

    @Size(min = 1, message = "É necessário possuir ações.")
    @NotBlank(message = "É necessário possuir ações.")
    private BigDecimal totalPrice;

    private UserDTOForObjects user;

    private OrderStatus status;

    @NotBlank(message = "É necessário incluir o horário de entrada.")
    private LocalDateTime dateTimeCreation;

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public @NotBlank(message = "É necessário incluir uma ação.") Stock getStock() {
        return stock;
    }

    public void setStock(@NotBlank(message = "É necessário incluir uma ação.") Stock stock) {
        this.stock = stock;
    }

    public @NotBlank(message = "É necessário incluir uma carteira.") Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(@NotBlank(message = "É necessário incluir uma carteira.") Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public @NotBlank(message = "É necessário incluir o horário de entrada.") LocalDateTime getDateTimeCreation() {
        return dateTimeCreation;
    }

    public LocalDateTime getDateTimeExecution() {
        return dateTimeExecution;
    }

    public void setDateTimeExecution(LocalDateTime dateTimeExecution) {
        this.dateTimeExecution = dateTimeExecution;
    }

    public UserDTOForObjects getUser() {
        return user;
    }

    public void setUser(UserDTOForObjects user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDateTimeCreation(@NotBlank(message = "É necessário incluir o horário de entrada.") LocalDateTime dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public @Size(min = 1, message = "É necessário possuir ações.") @NotBlank(message = "É necessário possuir ações.") Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@Size(min = 1, message = "É necessário possuir ações.") @NotBlank(message = "É necessário possuir ações.") Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public @Size(min = 1, message = "É necessário possuir o preço individual.") @NotBlank(message = "É necessário possuir o preço individual.") BigDecimal getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(@Size(min = 1, message = "É necessário possuir o preço individual.") @NotBlank(message = "É necessário possuir o preço individual.") BigDecimal rawPrice) {
        this.rawPrice = rawPrice;
    }

    public @Size(min = 1, message = "É necessário possuir ações.") @NotBlank(message = "É necessário possuir ações.") BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@Size(min = 1, message = "É necessário possuir ações.") @NotBlank(message = "É necessário possuir ações.") BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
