package tech.ada.jjh.homebroker.unit.service.patch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.model.*;
import tech.ada.jjh.homebroker.repository.UserRepository;
import tech.ada.jjh.homebroker.service.patch.PatchUserService;

import java.math.BigDecimal;
import java.util.HashMap;


@ExtendWith(MockitoExtension.class)
class PatchUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PatchUserService patchUserService;

    @Test
    void mustChangeTheUserBalanceGivenAnOrderToBuy(){
        AppUser user = new AppUser();
        user.setBalance(BigDecimal.valueOf(50));
        Order order = new Order();
        order.setUser(user);
        order.setType(OrderType.BUYING);
        order.setTotalPrice(BigDecimal.valueOf(50));

        patchUserService.modifyUserBalance(order);

        Assertions.assertEquals(BigDecimal.ZERO, user.getBalance());

    }

    @Test
    void mustChangeTheUserBalanceGivenAnOrderToSell(){
        AppUser user = new AppUser();
        user.setBalance(BigDecimal.ZERO);
        Order order = new Order();
        order.setUser(user);
        order.setType(OrderType.SELLING);
        order.setTotalPrice(BigDecimal.valueOf(50));

        patchUserService.modifyUserBalance(order);

        Assertions.assertEquals(BigDecimal.valueOf(50), user.getBalance());
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
    void mustModifyAnUsersPortfolioByAddingStocks(){
        AppUser user = new AppUser();
        user.setPortfolio(new HashMap<>());
        Stock stock = new Stock();
        Order order = new Order();
        order.setUser(user);
        order.setStock(stock);
        order.setStockQuantity(3);
        order.setType(OrderType.BUYING);

        patchUserService.modifyUserStock(order);

        Assertions.assertEquals(1, user.getPortfolio().size());
        Assertions.assertEquals(3, user.getPortfolio().get(stock));
    }

    @Test
    void mustEmptyAnUsersPortfolioByRemovingStocks(){
        AppUser user = new AppUser();
        user.setPortfolio(new HashMap<>());
        Stock stock = new Stock();
        user.getPortfolio().put(stock, 3);
        Order order = new Order();
        order.setUser(user);
        order.setStock(stock);
        order.setStockQuantity(3);
        order.setType(OrderType.SELLING);

        patchUserService.modifyUserStock(order);

        Assertions.assertTrue(user.getPortfolio().isEmpty());
    }

    @Test
    void mustModifyAnUsersPortfolioByRemovingStocks(){
        AppUser user = new AppUser();
        user.setPortfolio(new HashMap<>());
        Stock stock = new Stock();
        user.getPortfolio().put(stock, 3);
        Order order = new Order();
        order.setUser(user);
        order.setStock(stock);
        order.setStockQuantity(2);
        order.setType(OrderType.SELLING);

        patchUserService.modifyUserStock(order);

        Assertions.assertEquals(1, user.getPortfolio().size());
        Assertions.assertEquals(1, user.getPortfolio().get(stock));
    }
}