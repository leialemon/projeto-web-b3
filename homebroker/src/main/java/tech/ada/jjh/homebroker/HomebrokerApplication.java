package tech.ada.jjh.homebroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomebrokerApplication{
	public static void main(String[] args){
		SpringApplication.run(HomebrokerApplication.class, args);
	}

}

//TODO exceção de ações insuficientes quando usuário tentar vender ações que não possui.
//TODO lançar 404 quando não encontrar entidade no banco de dados.
//TODO lançar exceção quando o usuário tentar confirmar uma ordem que não está PENDING