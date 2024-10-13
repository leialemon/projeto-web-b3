package tech.ada.jjh.homebroker.service.fetch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.mapper.StockMapper;
import tech.ada.jjh.homebroker.repository.StockRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FetchStockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    StockMapper stockMapper;

    @InjectMocks
    FetchStockService fetchStockService;

    @Test
    void fetchById() {
    }

    @Test
    void fetchByTicker() {
    }

    @Test
    void getByTicker() {
    }

    @Test
    void fetchAll() {
    }
}