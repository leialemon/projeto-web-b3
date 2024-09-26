package tech.ada.jjh.homebroker.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.model.Stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderDTO{

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

    @Size(min = 4, max = 7, message = "O Ticker deve ter entre 4 e 7 caracteres")
    @NotBlank(message = "O ticker da ação não pode estar em branco.")
    private String ticker;


    public OrderDTO(Stock stock,
                    Portfolio portfolio,
                    LocalDateTime dateTimeExecution,
                    Integer stockQuantity,
                    BigDecimal rawPrice,
                    BigDecimal totalPrice,
                    String ticker,
                    List<Fee> fees
                    ){
        this.portfolio = portfolio;
        this.stock = stock;
        this.dateTimeExecution = dateTimeExecution;
        this.stockQuantity = stockQuantity;
        this.rawPrice = rawPrice;
        this.totalPrice = totalPrice;
        this.ticker = ticker;
        this.fees = fees;

    }

    public OrderDTO(){

    }

    public Order toEntity(){
        Order order = new Order();
        order.setFees(new ArrayList<>());
        order.setStock(this.stock);
        order.setRawPrice(this.rawPrice);
        order.setTotalPrice(this.totalPrice);
        order.setPortfolio(this.portfolio);
        order.setDateTimeExecution(this.dateTimeExecution);
        order.setStockQuantity(this.stockQuantity);
        return order;

    }

    public static OrderDTO fromEntity(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setFees(new ArrayList<>());
        dto.setStock(order.getStock());
        dto.setRawPrice(order.getRawPrice());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setPortfolio(order.getPortfolio());
        dto.setDateTimeExecution(order.getDateTimeExecution());
        dto.setStockQuantity(order.getStockQuantity());
        return dto;

    }

}