package tech.ada.jjh.homebroker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class AppUserDTORequest {

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "O nome não pode estar em branco.")
    private String name;

    @Size(min = 8, max = 32, message = "A senha deve ter entre 8 e 32 caracteres")
    @NotBlank(message = "A senha não pode estar em branco.")
    private String password;

    @Size(min = 10, max = 10, message = "O aniversário deve estar no formato 'DD/MM/AAAA'")
    @NotBlank(message = "O aniversário deve estar no formato 'DD/MM/AAAA'")
    private String birthDate;

    @CPF
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos.")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    public @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") @NotBlank(message = "O nome não pode estar em branco.") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") @NotBlank(message = "O nome não pode estar em branco.") String name) {
        this.name = name;
    }

    public @Size(min = 8, max = 32, message = "A senha deve ter entre 8 e 32 caracteres") @NotBlank(message = "A senha não pode estar em branco.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, max = 32, message = "A senha deve ter entre 8 e 32 caracteres") @NotBlank(message = "A senha não pode estar em branco.") String password) {
        this.password = password;
    }

    public @Size(min = 10, max = 10, message = "O aniversário deve estar no formato 'DD/MM/AAAA'") @NotBlank(message = "O aniversário deve estar no formato 'DD/MM/AAAA'") String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Size(min = 10, max = 10, message = "O aniversário deve estar no formato 'DD/MM/AAAA'") @NotBlank(message = "O aniversário deve estar no formato 'DD/MM/AAAA'") String birthDate) {
        this.birthDate = birthDate;
    }

    public @CPF @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos.") String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos.") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "Formato de e-mail inválido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "Formato de e-mail inválido.") String email) {
        this.email = email;
    }

//    public String getEmailProvider() {
//        return emailProvider;
//    }
//
//    private String setEmailProvider(String email){
//        if (email != null && email.contains("@")){
//            return email.substring(email.indexOf("@") + 1);
//        }
//        return null;
//    }
}
