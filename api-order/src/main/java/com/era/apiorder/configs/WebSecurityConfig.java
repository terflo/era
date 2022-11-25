package com.era.apiorder.configs;

import com.era.utils.security.JwtMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .mvcMatcher("/**")
                .authorizeRequests()
                .mvcMatchers("/actuator/**")
                .permitAll()
                .mvcMatchers("/api/**")
                .access("hasAuthority('SCOPE_order.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new JwtMethodSecurityExpressionHandler();
    }
}
