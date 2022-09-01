package com.era.authserver.model.entities.dto;

import com.era.authserver.model.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class UserDto {

    private String UUID;

    private String username;

    private String email;

    private Date timestamp;

    private Set<Role> roles;
}
