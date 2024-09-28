package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.AppUserDTOResponse;
import tech.ada.jjh.homebroker.dto.AppUserDTORequest;
import tech.ada.jjh.homebroker.dto.UserDTOForObjects;
import tech.ada.jjh.homebroker.model.AppUser;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserMapper{
    @Mapping(target = "transactionHistory", source = "transactionHistory")
    @Mapping(target = "orderHistory", source = "orderHistory")
    @Mapping(target = "stocks", source = "stocks")
    AppUserDTOResponse toAppUserDTOResponse(AppUser appUser);

    AppUser toAppUser(AppUserDTORequest appUserDTORequest);
    List<AppUserDTOResponse> listToDto(List<AppUser> users);
    UserDTOForObjects toDTOForObjects(AppUser appUser);
    UserDTOForObjects toDTOForObjects(AppUserDTOResponse appUserDTOResponse);
}
