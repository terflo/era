package com.era.authserver.model.services;

import com.era.authserver.model.entities.User;
import com.era.authserver.model.exceptions.UserAlreadyExistsException;
import com.era.authserver.model.exceptions.UserNotFoundException;
import com.era.authserver.model.repositories.UserRepository;
import com.era.authserver.model.requests.UserRegistrationRequest;
import com.era.authserver.model.services.interfaces.RoleService;
import com.era.authserver.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        return this.userRepository.save(User
                .builder()
                .UUID(UUID.randomUUID().toString())
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .email(registrationRequest.getEmail())
                .timestamp(new Date())
                .roles(Collections.singleton(this.roleService.getRoleByName("ROLE_USER")))
                .expired(false)
                .locked(false)
                .credentials_expired(false)
                .enabled(true)
                .build());
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUUID(String uuid) throws UserNotFoundException {
        return this.userRepository
                .getUserByUUID(uuid)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", uuid));
                });
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return this.userRepository
                .getUserByUsername(username)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("Пользователь с именем %s не найден", username));
                });
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException, UserAlreadyExistsException {
        //TODO: Написать метод
    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException {
        if(this.userRepository.existsUserByUUID(user.getUUID()))
            throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", user.getUUID()));
        this.userRepository.delete(user);
    }

    @Override
    public void deleteUserByUUID(String uuid) throws UserNotFoundException {
        if(this.userRepository.existsUserByUUID(uuid))
            throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", uuid));
        this.userRepository.deleteUserByUUID(uuid);
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        if(this.userRepository.existsUserByUsername(username))
            throw new UserNotFoundException(String.format("Пользователь с именем %s не найден", username));
        this.userRepository.deleteUserByUsername(username);
    }

    @Override
    public boolean userExistsByUUID(String uuid) {
        return this.userRepository.existsUserByUUID(uuid);
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return this.userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return this.userRepository.existsUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .getUserByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(
                            String.format("Пользователь с имененм %s не найден", username)
                    );
                });
    }
}
