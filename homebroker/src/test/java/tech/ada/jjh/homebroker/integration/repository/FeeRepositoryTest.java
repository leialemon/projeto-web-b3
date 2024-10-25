package tech.ada.jjh.homebroker.integration.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.model.FeeType;
import tech.ada.jjh.homebroker.repository.FeeRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class FeeRepositoryTest {

    Fee fee1;
    Fee fee2;

    @Autowired
    FeeRepository feeRepository;

    @BeforeEach
    public void setUp(){
        fee1 = new Fee();
        fee1.setType(FeeType.EMOLUMENT);
        fee1.setAmount(2D);
        fee1.setName("Test Fee 1");

        fee2 = new Fee();
        fee2.setType(FeeType.TAX);
        fee2.setAmount(2D);
        fee2.setName("Test Fee 2");

        feeRepository.save(fee1);
        feeRepository.save(fee2);
    }

    @AfterEach
    public void tearDown(){
        feeRepository.delete(fee1);
        feeRepository.delete(fee2);
    }

    @Test
    void mustSuccessfullySaveAFeeAndGenerateSequentialIDs(){
        String feeName = "Fee to be saved";
        Fee feeToBeSaved = new Fee();
        feeToBeSaved.setName(feeName);

        Fee savedFee = feeRepository.save(feeToBeSaved);

        Assertions.assertNotNull(savedFee);
        Assertions.assertEquals(feeName, savedFee.getName());
        Assertions.assertNotNull(savedFee.getId());
    }

    @Test
    void mustSuccessfullyFindASavedFeeById(){
        Optional<Fee> returned = feeRepository.findById(fee1.getId());

        Assertions.assertTrue(returned.isPresent());
        Assertions.assertEquals("Test Fee 1", returned.get().getName());
    }

    @Test
    void mustReturnAllSavedFees(){
        List<Fee> returnedList = feeRepository.findAll();

        Assertions.assertNotNull(returnedList);
        Assertions.assertFalse(returnedList.isEmpty());
    }

    @Test
    void mustUpdateASavedFee(){

        Optional<Fee> optionalFee = feeRepository.findById(fee1.getId());
        Fee modifySavedFee = optionalFee.orElseThrow();
        modifySavedFee.setName("Updated Test Fee 1");
        feeRepository.save(modifySavedFee);

        Optional<Fee> returned = feeRepository.findById(fee1.getId());

        Assertions.assertTrue(returned.isPresent());
        Assertions.assertEquals("Updated Test Fee 1", returned.get().getName());
    }
}