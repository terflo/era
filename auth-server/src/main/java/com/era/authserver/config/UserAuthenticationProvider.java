package com.era.authserver.config;

import com.era.authserver.model.entities.User;
import com.era.authserver.model.exceptions.UserNotFoundException;
import com.era.authserver.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {

            User user = userService.getUserByUsername(username);

            if(passwordEncoder.matches(password, user.getPassword())) {
                return UsernamePasswordAuthenticationToken.authenticated(username, password, Collections.emptyList());
            }

        } catch (UserNotFoundException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
