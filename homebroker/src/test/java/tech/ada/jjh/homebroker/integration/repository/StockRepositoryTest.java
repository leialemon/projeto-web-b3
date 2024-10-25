package tech.ada.jjh.homebroker.integration.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.ada.jjh.homebroker.model.Stock;
import tech.ada.jjh.homebroker.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class StockRepositoryTest {

    Stock stock1;

    @Autowired
    StockRepository stockRepository;

    @BeforeEach
    public void setUp(){
        stock1 = new Stock();
        stock1.setTicker("ticker 1");
        stock1.setName("stock 1");
        stockRepository.save(stock1);
    }

    @AfterEach
    public void tearDown(){
        stockRepository.delete(stock1);
    }

    @Test
    void mustSaveAStockAndGenerateASequentialId(){
        Stock stockToBeSaved = new Stock();
        stockToBeSaved.setTicker("ticker to be saved");

        Stock stockSaved = stockRepository.save(stockToBeSaved);

        Assertions.assertNotNull(stockSaved);
        Assertions.assertNotNull(stockSaved.getId());
        Assertions.assertEquals(stockToBeSaved.getTicker(), stockSaved.getTicker());
    }

    @Test
    void mustFindASavedStockById(){
        Optional<Stock> returnedStock = stockRepository.findById(stock1.getId());

        Assertions.assertTrue(returnedStock.isPresent());
        Assertions.assertEquals(stock1.getTicker(), returnedStock.get().getTicker());
    }

    @Test
    void mustFindASavedStockByTicker(){
        Optional<Stock> returnedStock = stockRepository.findByTicker(stock1.getTicker());

        Assertions.assertTrue(returnedStock.isPresent());
        Assertions.assertEquals(stock1.getName(), returnedStock.get().getName());
    }

    @Test
    void mustReturnAllSavedStocks(){
        List<Stock> savedStocksList = stockRepository.findAll();

        Assertions.assertNotNull(savedStocksList);
        Assertions.assertFalse(savedStocksList.isEmpty());
        Assertions.assertEquals(1, savedStocksList.size());
    }

    @Test
    void mustUpdateASavedStock(){
        String ticker = "updated ticker 1";
        Optional<Stock> stockToBeUpdated = stockRepository.findById(stock1.getId());
        Stock updatedStock = stockToBeUpdated.orElseThrow();
        updatedStock.setTicker(ticker);

        Optional<Stock> returnedStock = stockRepository.findByTicker(ticker);

        Assertions.assertTrue(returnedStock.isPresent());
        Assertions.assertEquals(stock1.getName(), returnedStock.get().getName());
    }
}