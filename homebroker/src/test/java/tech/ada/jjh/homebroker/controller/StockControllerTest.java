package tech.ada.jjh.homebroker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.service.create.CreateStockService;
import tech.ada.jjh.homebroker.service.delete.DeleteStockService;
import tech.ada.jjh.homebroker.service.fetch.FetchStockService;
import tech.ada.jjh.homebroker.service.patch.PatchStockService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StockControllerTest {

    private MockMvc mockMVC;

    @Mock
    private CreateStockService createStockService;
    @Mock
    private FetchStockService fetchStockService;
    @Mock
    private DeleteStockService deleteStockService;
    @Mock
    private PatchStockService patchStockService;
    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    void GetAllTest() throws Exception{
        when(fetchStockService.fetchAll()).thenReturn(Collections.emptyList());

        mockMVC.perform(get("/api/v1/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(fetchStockService).fetchAll();

    }

    @Test
    void FindStockByTickerTest() throws Exception{
        StockDTO stock = new StockDTO();
        when(fetchStockService.fetchByTicker(anyString())).thenReturn(Optional.of(stock));
        mockMVC.perform(get("/api/v1/stocks/ticker/{ticker}", "BBAS3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(fetchStockService).fetchByTicker("bbas3");

    }

    @Test
    void FindStockByIdTest() throws Exception{
        StockDTO stock = new StockDTO();
        when(fetchStockService.fetchById(any(Long.class))).thenReturn(Optional.of(stock));
        mockMVC.perform(get("/api/v1/stocks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(fetchStockService).fetchById(1L);

    }

    @Test
    void InsertStockTest() throws Exception{
        StockDTO stock = new StockDTO();
        when(createStockService.create(any(StockDTO.class))).thenReturn(stock);

        mockMVC.perform(post("/api/v1/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Banco do Brasil\", \"ticker\":\"BBAS3\", \"price\":\"44.3\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(createStockService).create(any(StockDTO.class));

    }

    @Test
    void DeleteStockTest() throws Exception{
        mockMVC.perform(delete("/api/v1/stocks/delete/{ticker}", "BBAS3"))
                .andExpect(status().isOk());

        verify(deleteStockService).deleteStockByTicker("bbas3");

    }

    @Test
    void AlterStockPriceTest() throws Exception{
        StockDTO stock = new StockDTO();
        when(patchStockService.alterStockPrice(anyString(), any(BigDecimal.class))).thenReturn(stock);
        mockMVC.perform(patch("/api/v1/stocks/alter/{ticker}/price", "BBAS3")
                        .param("price", "37.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(patchStockService).alterStockPrice("bbas3", new BigDecimal("37.0"));

    }

}
