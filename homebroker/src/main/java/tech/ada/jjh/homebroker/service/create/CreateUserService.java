package tech.ada.jjh.homebroker.service.create;

import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;

@Service
public class CreateUserService {
    //É aqui que deve ficar o check de idade?
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public AppUser createUser(AppUser appUser){
        return userRepository.save(appUser);
    }
}
