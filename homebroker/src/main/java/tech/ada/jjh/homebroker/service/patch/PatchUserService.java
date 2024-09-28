package tech.ada.jjh.homebroker.service.patch;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Order;
import tech.ada.jjh.homebroker.model.Transaction;
import tech.ada.jjh.homebroker.repository.UserRepository;

import java.math.BigDecimal;

@Service
public class PatchUserService {
    private final UserRepository userRepository;

    public PatchUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void modifyUserBalance(Transaction transaction){
        AppUser user = transaction.getAppUser();
        user.setBalance(transaction.getType().getBalanceModifier().calculate(user.getBalance(), transaction.getAmount()));
    }

    public void modifyUserBalance(Order order){
        AppUser user = order.getUser();
        user.setBalance(order.getType().getBalanceModifier().calculate(user.getBalance(), order.getTotalPrice()));
    }
}
