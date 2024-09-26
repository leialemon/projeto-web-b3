package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import tech.ada.jjh.homebroker.dto.AppUserDTO;
import tech.ada.jjh.homebroker.dto.StockDTO;
import tech.ada.jjh.homebroker.model.AppUser;
import tech.ada.jjh.homebroker.model.Stock;

@Mapper
public class AppUserModelMapper{
    private static final ModelMapper modelMapper = new ModelMapper();

    public static AppUserDTO toDto(AppUser appUser){
        return modelMapper.map(appUser, AppUserDTO.class);

    }

    public static AppUser toEntity(AppUserDTO appUserDTO){
        return modelMapper.map(appUserDTO, AppUser.class);

    }

}
