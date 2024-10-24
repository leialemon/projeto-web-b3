package tech.ada.jjh.homebroker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.jjh.homebroker.dto.OrderDTORequest;
import tech.ada.jjh.homebroker.dto.OrderDTOResponse;
import tech.ada.jjh.homebroker.model.OrderStatus;
import tech.ada.jjh.homebroker.model.OrderType;
import tech.ada.jjh.homebroker.service.create.CreateOrderService;
import tech.ada.jjh.homebroker.service.fetch.FetchOrderService;
import tech.ada.jjh.homebroker.service.patch.PatchOrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    private MockMvc mockMVC;

    @Mock
    private CreateOrderService createOrderService;
    @Mock
    private FetchOrderService fetchOrderService;
    @Mock
    private PatchOrderService patchOrderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(orderController).build();

    }

    @Test
    void FetchAllTest() throws Exception{
        when(fetchOrderService.findAll()).thenReturn(Collections.emptyList());

        mockMVC.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(fetchOrderService).findAll();

    }

    @Test
    public void CreateOrderTest() throws Exception{
        OrderDTORequest orderDTORequest = new OrderDTORequest();
        orderDTORequest.setStockTicker("BBAS3");
        orderDTORequest.setStockQuantity(10);
        orderDTORequest.setUserCpf("31642766091");
        orderDTORequest.setType(OrderType.BUYING);
        orderDTORequest.setUserPassword("SenhaInsegura");

        OrderDTOResponse orderDTOResponse = new OrderDTOResponse();
        orderDTOResponse.setUuid("123e4567-e89b-12d3-a456-426614174000");
        orderDTOResponse.setStockTicker("BBAS3");
        orderDTOResponse.setStockQuantity(10);
        orderDTOResponse.setRawPrice(new BigDecimal("370.00"));
        orderDTOResponse.setTotalPrice(new BigDecimal("390.00"));
        orderDTOResponse.setDateTimeCreation(LocalDateTime.now());
        orderDTOResponse.setStatus(OrderStatus.PENDING);
        orderDTOResponse.setType(OrderType.BUYING);
        when(createOrderService.create(any(OrderDTORequest.class))).thenReturn(orderDTOResponse);

        mockMVC.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDTORequest)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.uuid").value("123e4567-e89b-12d3-a456-426614174000"))
                        .andExpect(jsonPath("$.stockTicker").value("BBAS3"))
                        .andExpect(jsonPath("$.stockQuantity").value(10))
                        .andExpect(jsonPath("$.rawPrice").value(370.00))
                        .andExpect(jsonPath("$.totalPrice").value(390.00));

    }


    @Test
    void ExecuteOrderTest() throws Exception{
        OrderDTOResponse orderResponse = new OrderDTOResponse();
        when(fetchOrderService.findByUuid(anyString())).thenReturn(orderResponse);
        when(patchOrderService.executeOrder(any(OrderDTOResponse.class))).thenReturn(orderResponse);

        mockMVC.perform(patch("/api/v1/orders/confirm/{uuid}", "123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(fetchOrderService).findByUuid("123e4567-e89b-12d3-a456-426614174000");
        verify(patchOrderService).executeOrder(any(OrderDTOResponse.class));

    }

    @Test
    void CancelOrderTest() throws Exception{
        OrderDTOResponse orderResponse = new OrderDTOResponse();
        when(fetchOrderService.findByUuid(anyString())).thenReturn(orderResponse);
        when(patchOrderService.cancelOrder(any(OrderDTOResponse.class))).thenReturn(orderResponse);

        mockMVC.perform(patch("/api/v1/orders/cancel/{uuid}", "123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(fetchOrderService).findByUuid("123e4567-e89b-12d3-a456-426614174000");
        verify(patchOrderService).cancelOrder(any(OrderDTOResponse.class));

    }

}
