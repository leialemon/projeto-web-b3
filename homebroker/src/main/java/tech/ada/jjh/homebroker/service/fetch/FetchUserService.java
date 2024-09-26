package tech.ada.jjh.homebroker.service.fetch;


import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTOGet;
import tech.ada.jjh.homebroker.dto.AppUserDTOPost;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
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

    public Optional<AppUserDTOGet> fetchById(Long id){
        return userRepository.findById(id).map(appUserMapper::toAppUserDTOGet);
    }

    public Optional<AppUserDTOGet> fetchByCpf(String cpf){
        return userRepository.findByCpf(cpf).map(appUserMapper::toAppUserDTOGet);
    }

    public List<AppUserDTOGet> fetchAll(){
        return userRepository.findAll().stream().map(appUserMapper::toAppUserDTOGet).toList();
    }
}
