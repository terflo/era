package com.era.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    private final CustomAuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService)
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
