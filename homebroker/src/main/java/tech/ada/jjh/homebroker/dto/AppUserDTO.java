package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import tech.ada.jjh.homebroker.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AppUserDTO{
    private List<Transaction> transactionHistory;
    private List<Portfolio> portfolios;
    private List<Order> orderHistory;

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "O nome não pode estar em branco.")
    private String name;

    @Size(min = 8, max = 32, message = "O nome deve ter entre 8 e 32 caracteres")
    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    @Size(min = 10, max = 10, message = "O aniversário deve estar no formato 'DD/MM/AAAA'")
    @NotBlank(message = "O aniversário deve estar no formato 'DD/MM/AAAA'")
    private String birthDate;

    @CPF
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos.")
    private String cpf;

    @NotBlank(message = "O saldo não pode estar em branco.")
    private BigDecimal balance;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    private String emailProvider;


    public AppUserDTO(List<Transaction> transactionHistory,
                      List<Portfolio> portfolios,
                      List<Order> orderHistory,
                      String name,
                      String password,
                      String birthDate,
                      String cpf,
                      BigDecimal balance,
                      String email){
        this.transactionHistory = transactionHistory;
        this.portfolios = portfolios;
        this.orderHistory = orderHistory;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.balance = balance;
        this.email = email;
        this.emailProvider = setEmailProvider(email);

    }

    public AppUserDTO(){

    }

    private String setEmailProvider(String email){
        if (email != null && email.contains("@")){
            return email.substring(email.indexOf("@") + 1);

        }
        return null;

    }

    public AppUser toEntity(){
        AppUser appUser = new AppUser();
        appUser.setBalance(this.balance);
        appUser.setName(this.name);
        appUser.setCpf(this.cpf);
        appUser.setBirthDate(this.birthDate);
        appUser.setEmail(this.email);
        appUser.setPassword(this.password);
        appUser.setPortfolios(new ArrayList<>());
        appUser.setOrderHistory(new ArrayList<>());
        appUser.setTransactionHistory(new ArrayList<>());
        return appUser;

    }

    public static AppUserDTO fromEntity(AppUser appUser){
        AppUserDTO dto = new AppUserDTO();
        dto.setBalance(appUser.getBalance());
        dto.setName(appUser.getName());
        dto.setCpf(appUser.getCpf());
        dto.setBirthDate(appUser.getBirthDate());
        dto.setEmail(appUser.getEmail());
        dto.setPassword(appUser.getPassword());
        dto.setPortfolios(new ArrayList<>());
        dto.setOrderHistory(new ArrayList<>());
        dto.setTransactionHistory(new ArrayList<>());
        return dto;

    }

}
