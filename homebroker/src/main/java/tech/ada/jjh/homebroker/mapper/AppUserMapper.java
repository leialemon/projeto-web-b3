package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import tech.ada.jjh.homebroker.dto.AppUserDTOGet;
import tech.ada.jjh.homebroker.dto.AppUserDTOPost;
import tech.ada.jjh.homebroker.model.AppUser;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserMapper{

    AppUserDTOGet toAppUserDTOGet(AppUser appUser);
    AppUser toAppUser(AppUserDTOPost appUserDTOPost);
}
