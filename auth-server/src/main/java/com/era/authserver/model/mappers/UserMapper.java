package com.era.authserver.model.mappers;

import com.era.authserver.model.entities.User;
import com.era.authserver.model.requests.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.UUID", target = "UUID")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "token", target = "token")
    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user, String token);

    @Mapping(source = "encodedPassword", target = "password")
    User userDtoToUser(UserDto userDto, String encodedPassword);
}
