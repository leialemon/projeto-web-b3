package tech.ada.jjh.homebroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomebrokerApplication{

	public static void main(String[] args){
		SpringApplication.run(HomebrokerApplication.class, args);
	}
}
// TODO corrigir o user de Transaction em transactionHistory aparecendo como null
// TODO corrigir erro de transaction response aparecer com cpf null
