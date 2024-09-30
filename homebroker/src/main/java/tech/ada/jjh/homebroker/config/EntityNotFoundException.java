package tech.ada.jjh.homebroker.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "A entidade buscada não existe no banco de dados.")
public class EntityNotFoundException extends RuntimeException{

}
