package tech.ada.jjh.homebroker.dto;

import tech.ada.jjh.homebroker.model.Fee;
import java.util.List;

public class BrokerDTO {

    private String name;
    private List<Fee> fees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }
}
