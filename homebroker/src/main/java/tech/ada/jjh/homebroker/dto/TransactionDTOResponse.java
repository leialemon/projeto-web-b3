package tech.ada.jjh.homebroker.dto;

import tech.ada.jjh.homebroker.model.TransactionType;

import java.math.BigDecimal;

public class TransactionDTOResponse {
    private UserDTOForObjects user;

    private BigDecimal amount;

    private TransactionType type;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public UserDTOForObjects getUser() {
        return user;
    }

    public void setUser(UserDTOForObjects user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}