package tech.ada.jjh.homebroker.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.Portfolio;
import tech.ada.jjh.homebroker.model.Transaction;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AppUserDTOGet {
    private String cpf;
    private String name;
    private String email;
    private BigDecimal balance;
    private List<Transaction> transactionHistory;
    private List<Portfolio> portfolios;
    private List<Order> orderHistory;
}
