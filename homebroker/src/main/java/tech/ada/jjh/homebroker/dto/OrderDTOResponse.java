package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.model.Stock;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderDTOResponse {

    private List<Fee> fees;

    @NotBlank(message = "É necessário incluir uma ação.")
    private Stock stock;

    @NotBlank(message = "É necessário incluir uma carteira.")
    private Portfolio portfolio;

    @NotBlank(message = "É necessário incluir o horário de entrada.")
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

    //TODO ver como vai ficar a lógica do ticker

    @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres")
    @NotBlank(message = "O ticker da ação não pode estar em branco.")
    private String ticker;

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

    public @NotBlank(message = "É necessário incluir o horário de entrada.") LocalDateTime getDateTimeExecution() {
        return dateTimeExecution;
    }

    public void setDateTimeExecution(@NotBlank(message = "É necessário incluir o horário de entrada.") LocalDateTime dateTimeExecution) {
        this.dateTimeExecution = dateTimeExecution;
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

    public @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres") @NotBlank(message = "O ticker da ação não pode estar em branco.") String getTicker() {
        return ticker;
    }

    public void setTicker(@Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres") @NotBlank(message = "O ticker da ação não pode estar em branco.") String ticker) {
        this.ticker = ticker;
    }
}
