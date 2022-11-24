package com.era.authserver.model.services;

import com.era.authserver.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final WebClient webClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseEntity<User> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/user/" + username)
                        .build())
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("auth"))
                .retrieve()
                .toEntity(User.class)
                .doOnError(e -> {
                    throw new UsernameNotFoundException(e.getMessage());
                })
                .block();

        return Objects.requireNonNull(response).getBody();
    }
}
