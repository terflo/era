package com.era.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

@Configuration
public class ClientRepositoryConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {


        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("gateway-client-id")
                .clientSecret("{bcrypt}$2a$10$GniyOU8FbX03WkOZ3AI5hOVho5iDHoDXGDd9jkBO0ixeytLHXL39O")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://gateway-server:8083/login/oauth2/code/gateway")
                .redirectUri("http://gateway-server:8083/authorized")
                .scope(OidcScopes.OPENID)
                .scope("course.read")
                .scope("course.write")
                .scope("user.read")
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

}
