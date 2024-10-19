package tech.ada.jjh.homebroker.service.fetch;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FetchStockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    StockMapper stockMapper;

    @InjectMocks
    FetchStockService fetchStockService;

    @Test
    void mustReturnAnOptionalDTOStockWhenAnIdIsGiven() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setTicker("teste");
        StockDTO stockDTO = new StockDTO();
        stockDTO.setTicker(stock.getTicker());

        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDTO);
        Mockito.when(stockRepository.findById(stock.getId())).thenReturn(Optional.of(stock));

        Optional<StockDTO> retorno = fetchStockService.fetchById(1L);

        Assertions.assertNotNull(retorno);
        Assertions.assertTrue(retorno.isPresent());
        Assertions.assertEquals("teste", retorno.get().getTicker());
    }

    @Test
    void mustReturnAnOptionalDTOStockWhenATickerIsGiven() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setTicker("teste");
        StockDTO stockDTO = new StockDTO();
        stockDTO.setTicker(stock.getTicker());

        Mockito.when(stockMapper.toDto(stock)).thenReturn(stockDTO);
        Mockito.when(stockRepository.findByTicker(stock.getTicker())).thenReturn(Optional.of(stock));

        Optional<StockDTO> retorno = fetchStockService.fetchByTicker("teste");

        Assertions.assertNotNull(retorno);
        Assertions.assertTrue(retorno.isPresent());
        Assertions.assertEquals("teste", retorno.get().getTicker());
    }

    @Test
    void mustReturnAStockDTOWhenAValidTickerIsGiven() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setTicker("teste");

        Mockito.when(stockRepository.findByTicker(stock.getTicker())).thenReturn(Optional.of(stock));

        Stock retorno = fetchStockService.getByTicker("teste");

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals("teste", retorno.getTicker());
    }

    @Test
    void mustThrowAnExceptionWhenAnInvalidTickerIsGiven(){
        Mockito.when(stockRepository.findByTicker("teste")).thenReturn(Optional.empty());
        RuntimeException retorno = Assertions.assertThrows(RuntimeException.class, () -> fetchStockService.getByTicker("teste"));
        Assertions.assertNotNull(retorno);
        Assertions.assertInstanceOf(EntityNotFoundException.class, retorno);
    }

    @Test
    void mustReturnADTOListContainingAllSavedStocks() {
        Stock stock1 = new Stock();
        stock1.setId(1L);
        stock1.setTicker("stock1");
        StockDTO stockDTO1 = new StockDTO();
        stockDTO1.setTicker(stock1.getTicker());

        Stock stock2 = new Stock();
        stock2.setTicker("stock2");
        stock2.setId(2L);
        StockDTO stockDTO2 = new StockDTO();
        stockDTO2.setTicker(stock2.getTicker());

        List<Stock> listStocks = new ArrayList<>();
        listStocks.add(stock1);
        listStocks.add(stock2);
        List<StockDTO> listDto = new ArrayList<>();
        listDto.add(stockDTO1);
        listDto.add(stockDTO2);

        Mockito.when(stockRepository.findAll()).thenReturn(listStocks);
        Mockito.when(stockMapper.listToDto(listStocks)).thenReturn(listDto);

        List<StockDTO> retorno = fetchStockService.fetchAll();

        Assertions.assertNotNull(retorno);
        Assertions.assertFalse(retorno.isEmpty());
        Assertions.assertEquals("stock1", retorno.get(0).getTicker());
        Assertions.assertEquals("stock2", retorno.get(1).getTicker());
    }
}