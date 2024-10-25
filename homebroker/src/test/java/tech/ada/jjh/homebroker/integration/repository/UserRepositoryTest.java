package tech.ada.jjh.homebroker.integration.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    AppUser user;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        user = new AppUser();
        user.setName("Generic Name");
        user.setBirthDate("01/01/2001");
        user.setCpf("53835250094");
        user.setEmail("email@email.com");
        user.setPassword("password");

        userRepository.save(user);
    }

    @AfterEach
    public void tearDown(){
        userRepository.delete(user);
    }

    @Test
    void mustSaveAnUserAndGenerateASequentialId(){
        AppUser userToBeSaved = new AppUser();
        userToBeSaved.setName("John Doe");
        userToBeSaved.setBirthDate("02/02/2002");
        userToBeSaved.setCpf("16787299060");
        userToBeSaved.setEmail("new_email@email.com");
        userToBeSaved.setPassword("password");

        AppUser savedUser = userRepository.save(userToBeSaved);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals(userToBeSaved.getCpf(), savedUser.getCpf());
    }

    @Test
    void mustFindASavedUserById(){
        Optional<AppUser> returnedUser = userRepository.findById(user.getId());

        Assertions.assertTrue(returnedUser.isPresent());
        Assertions.assertEquals(user.getCpf(), returnedUser.get().getCpf());
    }

    @Test
    void mustFindASavedUserByCpf(){
        AppUser returnedUser = userRepository.findByCpf(user.getCpf());

        Assertions.assertNotNull(returnedUser);
        Assertions.assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    void mustReturnAllSavedUsers(){
        List<AppUser> savedUsersList = userRepository.findAll();

        Assertions.assertNotNull(savedUsersList);
        Assertions.assertFalse(savedUsersList.isEmpty());
        Assertions.assertEquals(1, savedUsersList.size());
        Assertions.assertEquals(user, savedUsersList.getFirst());
    }

    @Test
    void mustUpdateASavedUser(){
        String newName = "Jane Doe";
        AppUser userToBeUpdated = userRepository.findByCpf(user.getCpf());
        userToBeUpdated.setName(newName);
        userRepository.save(userToBeUpdated);

        AppUser returnedUser = userRepository.findByCpf(user.getCpf());

        Assertions.assertNotNull(returnedUser);
        Assertions.assertEquals(user.getId(), returnedUser.getId());
        Assertions.assertEquals(newName, returnedUser.getName());
    }
}