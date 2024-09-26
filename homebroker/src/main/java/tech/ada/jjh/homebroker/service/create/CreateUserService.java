package tech.ada.jjh.homebroker.service.create;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import tech.ada.jjh.homebroker.dto.AppUserDTO;
import tech.ada.jjh.homebroker.mapper.AppUserMapper;
import tech.ada.jjh.homebroker.mapper.AppUserModelMapper;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.repository.UserRepository;

@Service
public class CreateUserService {
    //É aqui que deve ficar o check de idade?
    private final UserRepository userRepository;
    private final AppUserMapper appUserMapper;

    public CreateUserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.appUserMapper = Mappers.getMapper(AppUserMapper.class);

    }

    public AppUserDTO execute(AppUserDTO appUserDTO){
        var entity = AppUserModelMapper.toEntity(appUserDTO);
        entity = userRepository.save(entity);
        return AppUserModelMapper.toDto(entity);

    }

}
