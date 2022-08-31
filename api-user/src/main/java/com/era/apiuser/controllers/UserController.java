package com.era.apiuser.controllers;

import com.era.apiuser.model.requests.UserRegistrationRequest;
import com.era.apiuser.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

/*
          return ResponseEntity.ok(webClient
                .get()
                .uri("/course")
                .retrieve()
                .bodyToMono(String.class)
                .block());
*/

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final WebClient webClient;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok("Get all users");
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid UserRegistrationRequest registrationRequest) {
        userService.addUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
