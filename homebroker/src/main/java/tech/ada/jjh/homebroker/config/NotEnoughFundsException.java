package tech.ada.jjh.homebroker.config;

import org.springframework.web.bind.annotation.ResponseStatus;


public class NotEnoughFundsException extends RuntimeException{
    public NotEnoughFundsException(String message){
        super(message);
    }

    public NotEnoughFundsException(String message, Throwable cause){
        super(message, cause);
    }
}
