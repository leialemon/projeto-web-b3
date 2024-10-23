package tech.ada.jjh.homebroker.service.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.model.Fee;
import tech.ada.jjh.homebroker.repository.FeeRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreateFeeServiceTest {

    @Mock
    FeeRepository feeRepository;

    @InjectMocks
    CreateFeeService createFeeService;

    @Test
    void mustCreateTheBasicFees(){
        List<Fee> retorno = createFeeService.createBasicFees();

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(2, retorno.size());
        Assertions.assertEquals("b3emoluments", retorno.get(0).getName());
        Assertions.assertEquals("taxes", retorno.get(1).getName());
    }
}