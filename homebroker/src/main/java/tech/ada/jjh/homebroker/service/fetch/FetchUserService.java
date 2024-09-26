package tech.ada.jjh.homebroker.service.fetch;


import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTO;
import tech.ada.jjh.homebroker.dto.StockDTO;
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

    public Optional<AppUserDTO> fetchById(Long id){
        return userRepository.findById(id).map(AppUserDTO::fromEntity);
    }

    public Optional<AppUserDTO> fetchByCpf(String cpf){
        return userRepository.findByCpf(cpf).map(AppUserDTO::fromEntity);
    }

    public List<AppUserDTO> fetchAll(){
        return userRepository.findAll().stream().map(AppUserDTO::fromEntity).toList();

    }

}
