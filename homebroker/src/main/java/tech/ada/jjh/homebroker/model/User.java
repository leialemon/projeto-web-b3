package tech.ada.jjh.homebroker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_birth", nullable = false)
    private String birthDate;

    @Column(name = "user_balance")
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionHistory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;


//    public List<Order> getOrderHistory(){
//        this.setOrderHistory(new ArrayList<>());
//        //pegar todas as orders dos portfolios, adicionar nessa lista e ordenar por data.
//        return this.orderHistory;
//    }
}
