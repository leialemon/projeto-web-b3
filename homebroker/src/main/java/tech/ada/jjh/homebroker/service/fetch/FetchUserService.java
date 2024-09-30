package tech.ada.jjh.homebroker.service.fetch;


import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.TransactionDTOResponse;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FetchUserService{

    private final UserRepository userRepository;
    private final AppUserMapper appUserMapper;

    public FetchUserService(UserRepository userRepository, AppUserMapper appUserMapper){
        this.userRepository = userRepository;
        this.appUserMapper = appUserMapper;
    }

    public Optional<AppUserDTOResponse> fetchById(Long id){
        return userRepository.findById(id).map(appUserMapper::toAppUserDTOResponse);
    }

    public AppUserDTOResponse fetchByCpf(String cpf){
        return appUserMapper.toAppUserDTOResponse(userRepository.findByCpf(cpf));
    }

    public AppUser getByCpf(String cpf){
        return userRepository.findByCpf(cpf);
    }

    public List<AppUserDTOResponse> fetchAll(){
        return userRepository.findAll().stream().map(appUserMapper::toAppUserDTOResponse).toList();
    }
}
