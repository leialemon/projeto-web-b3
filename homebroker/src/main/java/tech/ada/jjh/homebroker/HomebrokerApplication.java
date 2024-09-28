package tech.ada.jjh.homebroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomebrokerApplication{

	public static void main(String[] args){
		SpringApplication.run(HomebrokerApplication.class, args);
	}
}
// TODO corrigir o OrderHistory do usuário aparecendo como null