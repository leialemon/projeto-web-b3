package tech.ada.jjh.homebroker.service.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.jjh.homebroker.config.IsAMinorException;
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
        appUserDTORequest.setBirthDate("11/11/1995");
        appUserDTORequest.setName("Test Name");
        AppUserDTOResponse appUserDTOResponse = new AppUserDTOResponse();
        appUserDTOResponse.setName("Test Name");
        AppUser appUser = new AppUser();
        appUser.setBirthDate("11/11/1995");
        appUser.setName("Test Name");

        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUser(appUserDTORequest)).thenReturn(appUser);
        Mockito.when(userMapper.toAppUserDTOResponse(appUser)).thenReturn(appUserDTOResponse);

        AppUserDTOResponse returned = createUserService.create(appUserDTORequest);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals("Test Name", returned.getName());
    }

    @Test
    void mustThrownExceptionWhenUserIsAMinor(){
        AppUserDTORequest appUserDTORequest = new AppUserDTORequest();
        AppUser appUser = new AppUser();
        appUser.setBirthDate("11/11/2015");
        appUserDTORequest.setBirthDate(appUser.getBirthDate());

        Mockito.when(userMapper.toAppUser(appUserDTORequest)).thenReturn(appUser);

        RuntimeException returned = Assertions.assertThrows(IsAMinorException.class, () -> createUserService.create(appUserDTORequest));

        Assertions.assertInstanceOf(IsAMinorException.class, returned);
    }

}