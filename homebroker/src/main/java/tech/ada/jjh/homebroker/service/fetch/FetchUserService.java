package tech.ada.jjh.homebroker.service.fetch;


import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FetchUserService{

    private final UserRepository userRepository;

    public FetchUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<AppUser> fetchById(Long id){
        return userRepository.findById(id);
    }

    public Optional<AppUser> fetchByCpf(String cpf){
        return userRepository.findByCpf(cpf);
    }

    public List<AppUser> fetchAll(){
        return userRepository.findAll();
    }
}
