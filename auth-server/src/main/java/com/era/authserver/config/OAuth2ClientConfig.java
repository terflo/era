package com.era.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public ReactiveClientRegistrationRepository reactiveClientRegistrationRepository() {
        return new InMemoryReactiveClientRegistrationRepository(
                ClientRegistration
                        .withRegistrationId("auth")
                        .clientId("auth-client-id")
                        .clientSecret("auth-very-secret-token")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .scope(Arrays.asList("user.read", "user.write"))
                        .tokenUri("http://auth-server:8085/oauth2/token")
                        .build()
        );
    }

}
