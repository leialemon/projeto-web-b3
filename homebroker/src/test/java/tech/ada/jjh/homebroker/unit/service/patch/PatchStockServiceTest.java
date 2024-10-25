package tech.ada.jjh.homebroker.unit.service.patch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.config.EntityNotFoundException;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;
import tech.ada.jjh.homebroker.service.patch.PatchStockService;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PatchStockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    StockMapper stockMapper;

    @InjectMocks
    PatchStockService patchStockService;

    @Test
    void mustThrowAnExceptionGivenTheStockDoesNotExist(){
        RuntimeException retorno = Assertions.assertThrows(RuntimeException.class , () -> patchStockService.alterStockPrice("ticker", BigDecimal.ZERO));
        Assertions.assertNotNull(retorno);
        Assertions.assertInstanceOf(EntityNotFoundException.class, retorno);
    }

    @Test
    void mustAlterThePriceOfTheGivenStock(){
        Stock stock = new Stock();
        stock.setPrice(BigDecimal.valueOf(10));
        stock.setTicker("ticker");

        StockDTO stockDTO = new StockDTO();

        Mockito.when(stockRepository.findByTicker(stock.getTicker())).thenReturn(Optional.of(stock));
        Mockito.when(stockRepository.save(stock)).thenReturn(stock);
        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDTO);

        patchStockService.alterStockPrice("ticker", BigDecimal.ZERO);
        stockDTO.setPrice(stock.getPrice());

        Assertions.assertEquals(BigDecimal.ZERO, stockDTO.getPrice());
    }
}