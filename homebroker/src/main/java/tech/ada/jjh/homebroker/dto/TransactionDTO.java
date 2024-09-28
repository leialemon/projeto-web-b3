package tech.ada.jjh.homebroker.dto;

import tech.ada.jjh.homebroker.model.TransactionType;

import java.math.BigDecimal;

public class TransactionDTO {
    private String userCpf;

    private BigDecimal amount;

    private TransactionType type;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
