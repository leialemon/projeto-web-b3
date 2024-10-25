package tech.ada.jjh.homebroker.integration.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Transaction;
import tech.ada.jjh.homebroker.model.TransactionStatus;
import tech.ada.jjh.homebroker.model.TransactionType;
import tech.ada.jjh.homebroker.repository.TransactionRepository;
import tech.ada.jjh.homebroker.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class TransactionRepositoryTest {

    Transaction transaction1;
    AppUser user;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        user = new AppUser();
        user.setName("Generic Name");
        user.setBirthDate("01/01/2001");
        user.setCpf("53835250094");
        user.setEmail("email@email.com");
        user.setPassword("password");

        transaction1 = new Transaction();
        transaction1.setAppUser(user);
        transaction1.setAmount(BigDecimal.valueOf(25));
        transaction1.setType(TransactionType.DEPOSIT);
        transaction1.setStatus(TransactionStatus.COMPLETED);

        userRepository.save(user);
        transactionRepository.save(transaction1);
    }

    @AfterEach
    public void tearDown(){
        transactionRepository.delete(transaction1);
        userRepository.delete(user);
    }

    @Test
    void mustSaveATransactionAndGenerateASequentialId(){
        Transaction transactionToBeSaved = new Transaction();
        transactionToBeSaved.setAppUser(user);
        transactionToBeSaved.setType(TransactionType.WITHDRAWAL);
        transactionToBeSaved.setStatus(TransactionStatus.CANCELED);
        transactionToBeSaved.setAmount(BigDecimal.TEN);

        Transaction transactionSaved = transactionRepository.save(transactionToBeSaved);

        Assertions.assertNotNull(transactionSaved);
        Assertions.assertNotNull(transactionSaved.getId());
        Assertions.assertEquals(transactionToBeSaved.getAmount(), transactionSaved.getAmount());
    }

    @Test
    void mustFindASavedStockById(){
        Optional<Transaction> returnedTransaction = transactionRepository.findById(transaction1.getId());

        Assertions.assertTrue(returnedTransaction.isPresent());
        Assertions.assertEquals(transaction1.getAmount(), returnedTransaction.get().getAmount());
    }

    @Test
    void mustReturnAllSavedTransactions(){
        List<Transaction> savedTransactionsList = transactionRepository.findAll();

        Assertions.assertNotNull(savedTransactionsList);
        Assertions.assertFalse(savedTransactionsList.isEmpty());
        Assertions.assertEquals(1, savedTransactionsList.size());
    }

    @Test
    void mustUpdateASavedTransaction(){
        AppUser updatedUser = new AppUser();
        updatedUser.setName("updated user");
        userRepository.save(updatedUser);

        Optional<Transaction> transactionToBeUpdated = transactionRepository.findById(transaction1.getId());
        Transaction updatedTransaction = transactionToBeUpdated.orElseThrow();
        updatedTransaction.setAppUser(updatedUser);
        transactionRepository.save(updatedTransaction);

        Optional<Transaction> returnedTransaction = transactionRepository.findById(transaction1.getId());

        Assertions.assertTrue(returnedTransaction.isPresent());
        Assertions.assertEquals(transaction1.getAmount(), returnedTransaction.get().getAmount());
        Assertions.assertEquals(updatedUser.getName(), returnedTransaction.get().getAppUser().getName());
    }
}