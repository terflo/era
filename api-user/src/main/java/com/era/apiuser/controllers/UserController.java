package com.era.apiuser.controllers;

import com.era.apiuser.model.entities.User;
import com.era.apiuser.model.requests.UserRegistrationRequest;
import com.era.apiuser.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable(value = "username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsersWithParams (
            @RequestParam(value = "uuid", required = false, defaultValue = "") String uuid,
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(userService.getUserByParams(uuid, username, email, PageRequest.of(pageNumber, pageSize)));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.addUser(userRegistrationRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam (value = "uuid") String uuid) {
        userService.deleteUserByUUID(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
}
