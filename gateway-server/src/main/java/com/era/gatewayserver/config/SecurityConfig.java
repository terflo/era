package com.era.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {

        /*
          Отключение авторизации для определенных маршрутов ([GET] /api/products)
         */

        httpSecurity
                .csrf()
                .disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/api/products/**", "/api/materials/**", "/api/deliveries/**", "/api/payTypes/**", "/api/categories/**")
                .permitAll()
                .pathMatchers(HttpMethod.POST, "/api/products/**", "/api/materials/**", "/api/deliveries/**", "/api/payTypes/**", "/api/categories/**")
                .permitAll()
                .pathMatchers(HttpMethod.PUT, "/api/products/**", "/api/materials/**", "/api/deliveries/**", "/api/payTypes/**", "/api/categories/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login();

        return httpSecurity.build();
    }

}
