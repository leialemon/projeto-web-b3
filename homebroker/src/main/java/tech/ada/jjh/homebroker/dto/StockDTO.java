package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class StockDTO{

    private BigDecimal price;

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "O nome da ação não pode estar em branco.")
    private String name;

    @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres")
    @NotBlank(message = "O ticker da ação não pode estar em branco.")
    private String ticker;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotBlank(message = "O preço não pode estar em branco.") BigDecimal price) {
        this.price = price;
    }

    public @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") @NotBlank(message = "O nome da ação não pode estar em branco.") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") @NotBlank(message = "O nome da ação não pode estar em branco.") String name) {
        this.name = name;
    }

    public @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres") @NotBlank(message = "O ticker da ação não pode estar em branco.") String getTicker() {
        return ticker;
    }

    public void setTicker(@Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres") @NotBlank(message = "O ticker da ação não pode estar em branco.") String ticker) {
        this.ticker = ticker;
    }
}
