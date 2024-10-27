package tech.ada.jjh.homebroker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.service.create.CreateUserService;
import tech.ada.jjh.homebroker.service.fetch.FetchUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest{
    private MockMvc mockMVC;
    @Mock
    private CreateUserService createUserService;
    @Mock
    private FetchUserService fetchUserService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    void GetAllTest() throws Exception{
        List<AppUserDTOResponse> users = Arrays.asList(new AppUserDTOResponse(), new AppUserDTOResponse());
        when(fetchUserService.fetchAll()).thenReturn(users);

        mockMVC.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(users.size()));

        verify(fetchUserService, times(1)).fetchAll();

    }

    @Test
    void FindUserByCpfTest() throws Exception{
        String cpf = "88276227058";
        AppUserDTOResponse user = new AppUserDTOResponse();
        when(fetchUserService.fetchByCpf(cpf)).thenReturn(user);

        mockMVC.perform(get("/api/v1/users/cpf/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(fetchUserService, times(1)).fetchByCpf(cpf);

    }

    @Test
    void CreateUserTest() throws Exception{
        AppUserDTORequest validAppUserDTORequest = new AppUserDTORequest();
        validAppUserDTORequest.setCpf("88276227058");
        validAppUserDTORequest.setName("Nahida");
        validAppUserDTORequest.setPassword("DendroUser");
        validAppUserDTORequest.setBirthDate("27/10/1524");
        validAppUserDTORequest.setEmail("Nahida@Sumeru.com");

        AppUserDTOResponse expectedResponse = new AppUserDTOResponse();
        expectedResponse.setCpf(validAppUserDTORequest.getCpf());
        expectedResponse.setName(validAppUserDTORequest.getName());
        expectedResponse.setEmail(validAppUserDTORequest.getEmail());
        expectedResponse.setBalance(BigDecimal.valueOf(1000.0));

        when(createUserService.create(any(AppUserDTORequest.class))).thenReturn(expectedResponse);

        mockMVC.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validAppUserDTORequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf").value(validAppUserDTORequest.getCpf()))
                .andExpect(jsonPath("$.name").value(validAppUserDTORequest.getName()))
                .andExpect(jsonPath("$.email").value(validAppUserDTORequest.getEmail()))
                .andExpect(jsonPath("$.balance").value("1000.0"));
    
    }

}
