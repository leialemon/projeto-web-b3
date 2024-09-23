package com.JJH.homebroker.model;

import com.JJH.homebroker.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
