package tech.ada.jjh.homebroker.service.patch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Transaction;
import tech.ada.jjh.homebroker.model.TransactionType;
import tech.ada.jjh.homebroker.repository.UserRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatchUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PatchUserService patchUserService;

    @Test
    void mustChangeTheUserBalanceGivenAnOrderToBuy(){


    }

    @Test
    void mustChangeTheUserBalanceGivenAnOrderToSell(){

    }

    @Test
    void mustChangeTheUserBalanceGivenAnTransactionDeposit(){
        AppUser user = new AppUser();
        user.setBalance(BigDecimal.ZERO);
        Transaction transaction = new Transaction();
        transaction.setAppUser(user);
        transaction.setAmount(BigDecimal.valueOf(50));
        transaction.setType(TransactionType.DEPOSIT);

        patchUserService.modifyUserBalance(transaction);

        Assertions.assertEquals(BigDecimal.valueOf(50), user.getBalance());
    }

    @Test
    void mustChangeTheUserBalanceGivenAnTransactionWithdrawal(){
        AppUser user = new AppUser();
        user.setBalance(BigDecimal.valueOf(50));
        Transaction transaction = new Transaction();
        transaction.setAppUser(user);
        transaction.setAmount(BigDecimal.valueOf(50));
        transaction.setType(TransactionType.WITHDRAWAL);

        patchUserService.modifyUserBalance(transaction);

        Assertions.assertEquals(BigDecimal.ZERO, user.getBalance());
    }

    @Test
    void mustModifyAnUsersPortfolio(){}
}