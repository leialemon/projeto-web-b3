package tech.ada.jjh.homebroker.service.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
class CreateStockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    StockMapper stockMapper;

    @InjectMocks
    CreateStockService createStockService;

    @Test
    void mustReturnTheCreatedStock(){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setName("Test Stock");
        Stock stock = new Stock();

        Mockito.when(stockMapper.toEntity(stockDTO)).thenReturn(stock);
        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDTO);
        Mockito.when(stockRepository.save(stock)).thenReturn(stock);

        StockDTO returned = createStockService.create(stockDTO);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals("Test Stock", returned.getName());
    }

}