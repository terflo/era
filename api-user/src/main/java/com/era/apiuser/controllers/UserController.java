package com.era.apiuser.controllers;

import com.era.apiuser.model.entities.User;
import com.era.apiuser.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    //@PreAuthorize("jwtRoleAllowed('ROLE_USER')")
    public User getUsersWithParams (
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email) {
        System.out.println(username);
        return userService.getUserByUsername(username);
    }
}
