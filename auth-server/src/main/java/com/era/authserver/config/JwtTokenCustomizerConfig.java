package com.era.authserver.config;

import com.era.authserver.model.exceptions.UserNotFoundException;
import com.era.authserver.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

@Configuration
@RequiredArgsConstructor
public class JwtTokenCustomizerConfig {

    private final UserService userService;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() throws UserNotFoundException {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
                    String username = (String) claims.get("sub");
                    claims.put("authorities", userService.getUserByUsername(username).getRoleNames());
                });
            }
        };
    }

}
