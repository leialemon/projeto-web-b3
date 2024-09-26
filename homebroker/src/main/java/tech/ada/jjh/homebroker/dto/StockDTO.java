package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ada.jjh.homebroker.model.Stock;

import java.math.BigDecimal;

@Getter @Setter
public class StockDTO{
    @NotBlank(message = "O preço não pode estar em branco.")
    private BigDecimal price;

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "O nome da ação não pode estar em branco.")
    private String name;

    @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres")
    @NotBlank(message = "O ticker da ação não pode estar em branco.")
    private String ticker;


    public StockDTO(BigDecimal price, String name, String ticker){
        this.price = price;
        this.name = name;
        this.ticker = ticker;

    }

    public StockDTO(){

    }

    public Stock toEntity() {
        Stock stock = new Stock();
        stock.setPrice((this.price));
        stock.setName(this.name);
        stock.setTicker(this.ticker);
        return stock;

    }

    public static StockDTO fromEntity(Stock stock){
        StockDTO dto = new StockDTO();
        dto.setPrice(stock.getPrice());
        dto.setName(stock.getName());
        dto.setTicker(stock.getTicker());
        return dto;

    }

}
