package com.era.authserver;

import com.era.authserver.model.requests.UserRegistrationRequest;
import com.era.authserver.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class RunTest implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.addUser(new UserRegistrationRequest("testtest", "test@test.ru", "testtest"));
    }
}
