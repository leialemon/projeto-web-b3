package tech.ada.jjh.homebroker.service.create;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.AccessUser;
import tech.ada.jjh.homebroker.repository.AccessUsersRepository;

@Service
public class CreateAccessUserService {

    private final AccessUsersRepository accessUsersRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CreateAccessUserService(AccessUsersRepository accessUsersRepository){
        this.accessUsersRepository = accessUsersRepository;
    }

    public AccessUser createAccessUser(String username, String password){
        AccessUser user = new AccessUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return accessUsersRepository.save(user);
    }
}
