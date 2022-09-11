package com.era.authserver.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    @ToString.Exclude
    @JsonIgnore
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.name;
    }
}