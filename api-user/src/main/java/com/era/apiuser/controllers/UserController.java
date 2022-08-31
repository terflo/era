package com.era.apiuser.controllers;

import com.era.apiuser.model.requests.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

/*
*         return ResponseEntity.ok(webClient
                .get()
                .uri("/course")
                .retrieve()
                .bodyToMono(String.class)
                .block());
* */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final WebClient webClient;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok("Get all users");
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid UserRegistrationRequest registrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
