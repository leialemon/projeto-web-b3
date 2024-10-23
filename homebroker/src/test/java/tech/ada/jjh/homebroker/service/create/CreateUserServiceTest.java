package tech.ada.jjh.homebroker.service.create;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AppUserMapper userMapper;

    @InjectMocks
    CreateUserService createUserService;

    @Test
    void mustCreateUser(){
        AppUserDTORequest appUserDTORequest = new AppUserDTORequest();
        AppUserDTOResponse appUserDTOResponse = new AppUserDTOResponse();
        AppUser appUser = new AppUser();

        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUser(appUserDTORequest)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUserDTOResponse(appUser)).thenReturn(appUserDTOResponse);
    }

    @Test
    void mustThrownExceptionWhenUserIsAMinor(){
        AppUserDTORequest appUserDTORequest = new AppUserDTORequest();
        AppUserDTOResponse appUserDTOResponse = new AppUserDTOResponse();
        AppUser appUser = new AppUser();

        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUser(appUserDTORequest)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUserDTOResponse(appUser)).thenReturn(appUserDTOResponse);
    }

}