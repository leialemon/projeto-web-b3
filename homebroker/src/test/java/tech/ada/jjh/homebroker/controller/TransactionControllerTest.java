package tech.ada.jjh.homebroker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.jjh.homebroker.dto.TransactionDTORequest;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.service.create.CreateTransactionService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionControllerTest {
    private MockMvc mockMVC;

    @Mock
    private CreateTransactionService createTransactionService;
    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(transactionController).build();

    }

    @Test
    void CreateTransactionTest() throws Exception{
        TransactionDTOResponse transactionResponse = new TransactionDTOResponse();
        when(createTransactionService.createTransaction(any(TransactionDTORequest.class))).thenReturn(transactionResponse);
        mockMVC.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"stockId\":1, \"quantity\":10, \"price\":\"370.0\"}"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(createTransactionService).createTransaction(any(TransactionDTORequest.class));

    }

}
