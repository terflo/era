package com.era.authserver.config;

import com.era.authserver.model.requests.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserAuthenticationConverter implements AuthenticationConverter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Authentication convert(HttpServletRequest request) {

        AuthRequest authRequest;

        try {
            authRequest = MAPPER.readValue(request.getInputStream(), AuthRequest.class);
        } catch (IOException ignored) {
            return null;
        }
        return UsernamePasswordAuthenticationToken
                .unauthenticated(authRequest.getUsername(), authRequest.getPassword());
    }
}
