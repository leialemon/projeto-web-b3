package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "fee_amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "fee_type", nullable = false)
    private FeeType type;

    @Column(name = "fee_name", nullable = false)
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public FeeType getType() {
        return type;
    }

    public void setType(FeeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
