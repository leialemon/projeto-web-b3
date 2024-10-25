package tech.ada.jjh.homebroker.unit.service.delete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.config.EntityNotFoundException;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;
import tech.ada.jjh.homebroker.service.delete.DeleteStockService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeleteStockServiceTest {

    @InjectMocks
    DeleteStockService deleteStockService;

    @Mock
    StockRepository stockRepository;

    @Test
    void mustSuccessfullyDeleteAnExistingStock() {
        Stock stock = new Stock();
        stock.setTicker("TESTE");
        Mockito.when(stockRepository.findByTicker("TESTE")).thenReturn(Optional.of(stock));

        deleteStockService.deleteStockByTicker("TESTE");

        Mockito.verify(stockRepository, Mockito.times(1)).delete(stock);
    }

    @Test
    void mustReturnEntityNotFoundExceptionSinceTheStockDoesNotExist(){
        Mockito.when(stockRepository.findByTicker("TESTE")).thenReturn(Optional.empty());

        RuntimeException test = Assertions.assertThrows(EntityNotFoundException.class, () -> deleteStockService.deleteStockByTicker("TESTE"));

        Assertions.assertNotNull(test);
    }
}