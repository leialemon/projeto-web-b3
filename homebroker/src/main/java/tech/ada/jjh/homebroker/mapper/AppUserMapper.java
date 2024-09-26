package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.factory.Mappers;
import tech.ada.jjh.homebroker.dto.AppUserDTO;
import tech.ada.jjh.homebroker.model.AppUser;

public interface AppUserMapper{
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    AppUserDTO toDto(AppUser appUser);

    AppUser toEntity(AppUserDTO appUserDTO);

}
