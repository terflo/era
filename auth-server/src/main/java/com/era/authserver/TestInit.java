package com.era.authserver;

import com.era.authserver.model.requests.UserRegistrationRequest;
import com.era.authserver.model.services.interfaces.RoleService;
import com.era.authserver.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestInit implements CommandLineRunner {

    private final RoleService roleService;

    private final UserService userService;

    @Override
    public void run(String... args) {
        //userService.addUser(new UserRegistrationRequest("testtest", "test@test.test", "testtest"));
    }
}
