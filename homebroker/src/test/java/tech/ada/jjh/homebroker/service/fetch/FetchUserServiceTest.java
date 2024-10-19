package tech.ada.jjh.homebroker.service.fetch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FetchUserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AppUserMapper appUserMapper;

    @InjectMocks
    FetchUserService fetchUserService;

    @Test
    void mustReturnAnUserDtoWhenAValidIdIsGiven(){
        AppUser user = new AppUser();
        user.setId(1L);
        user.setName("test user");
        AppUserDTOResponse userDTOResponse = new AppUserDTOResponse();
        userDTOResponse.setName(user.getName());

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(appUserMapper.toAppUserDTOResponse(user)).thenReturn(userDTOResponse);

        Optional<AppUserDTOResponse> retorno = fetchUserService.fetchById(1L);

        Assertions.assertNotNull(retorno);
        Assertions.assertTrue(retorno.isPresent());
        Assertions.assertEquals("test user", retorno.get().getName());
    }

    @Test
    void mustReturnAnUserDTOWhenGivenAValidCpf(){
        AppUser user = new AppUser();
        user.setId(1L);
        user.setName("test user");
        user.setCpf("12345");
        AppUserDTOResponse userDTOResponse = new AppUserDTOResponse();
        userDTOResponse.setName(user.getName());
        userDTOResponse.setCpf(user.getCpf());

        Mockito.when(userRepository.findByCpf(user.getCpf())).thenReturn(user);
        Mockito.when(appUserMapper.toAppUserDTOResponse(user)).thenReturn(userDTOResponse);

        AppUserDTOResponse retorno = fetchUserService.fetchByCpf(user.getCpf());

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(user.getCpf(), retorno.getCpf());
        Assertions.assertEquals(user.getName(), retorno.getName());
    }

    @Test
    void mustReturnAnUserWhenGivenAValidCpf(){
        AppUser user = new AppUser();
        user.setId(1L);
        user.setName("test user");
        user.setCpf("12345");

        Mockito.when(userRepository.findByCpf(user.getCpf())).thenReturn(user);

        AppUser retorno = fetchUserService.getByCpf(user.getCpf());

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(user.getCpf(), retorno.getCpf());
        Assertions.assertEquals(user.getName(), retorno.getName());
    }

    @Test
    void mustReturnAListOfDtoUsers(){
        AppUser user1 = new AppUser();
        user1.setName("test user 1");
        AppUserDTOResponse userDTOResponse1 = new AppUserDTOResponse();
        userDTOResponse1.setName(user1.getName());

        AppUser user2 = new AppUser();
        user2.setName("test user 2");
        AppUserDTOResponse userDTOResponse2 = new AppUserDTOResponse();
        userDTOResponse2.setName(user2.getName());

        List<AppUser> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);


        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(appUserMapper.toAppUserDTOResponse(user1)).thenReturn(userDTOResponse1);
        Mockito.when(appUserMapper.toAppUserDTOResponse(user2)).thenReturn(userDTOResponse2);

        List<AppUserDTOResponse> retorno = fetchUserService.fetchAll();

        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(2, retorno.size());
        Assertions.assertEquals(user1.getName(), retorno.get(0).getName());
        Assertions.assertEquals(user2.getName(), retorno.get(1).getName());
    }
}