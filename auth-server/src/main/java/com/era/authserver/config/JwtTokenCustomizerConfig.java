package com.era.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class JwtTokenCustomizerConfig {

    private final UserDetailsService userService;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() throws UsernameNotFoundException {
        return (context) -> {

            /*
             * Для межмикросервисного взаимодействия используется тип CLIENT_CREDENTIALS и
             * к токенам для данных клиентов искать пользовательские роли в сервисе api-user не надо
             */
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) &&
                    !context.getAuthorizationGrantType().equals(AuthorizationGrantType.CLIENT_CREDENTIALS)) {

                context.getClaims().claims((claims) -> {
                    String username = (String) claims.get("sub");
                    claims.put("authorities", userService
                            .loadUserByUsername(username)
                            .getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toSet()));
                });
            }
        };
    }

}
