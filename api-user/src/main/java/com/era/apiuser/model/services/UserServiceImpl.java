package com.era.apiuser.model.services;

import com.era.apiuser.model.entities.User;
import com.era.apiuser.model.exceptions.UserAlreadyExistsException;
import com.era.apiuser.model.exceptions.UserNotFoundException;
import com.era.apiuser.model.repositories.UserRepository;
import com.era.apiuser.model.requests.UserRegistrationRequest;
import com.era.apiuser.model.services.interfaces.RoleService;
import com.era.apiuser.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        return userRepository.save(User
                .builder()
                .UUID(UUID.randomUUID().toString())
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .email(registrationRequest.getEmail())
                .timestamp(new Date())
                .roles(Collections.singleton(roleService.getRoleByName("ROLE_USER")))
                .expired(false)
                .locked(false)
                .credentials_expired(false)
                .enabled(true)
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUserByParams(String uuid, String username, String email, Pageable page) {
        return userRepository.getUserByUUIDOrUsernameOrEmail(uuid, username, email, page);
    }

    @Override
    public User getUserByUUID(String uuid) throws UserNotFoundException {
        return userRepository
                .getUserByUUID(uuid)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", uuid));
                });
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository
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
        if(userRepository.existsUserByUUID(user.getUUID()))
            throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", user.getUUID()));
        userRepository.delete(user);
    }

    @Override
    public void deleteUserByUUID(String uuid) throws UserNotFoundException {
        if(userRepository.existsUserByUUID(uuid))
            throw new UserNotFoundException(String.format("Пользователь с UUID %s не найден", uuid));
        userRepository.deleteUserByUUID(uuid);
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        if(userRepository.existsUserByUsername(username))
            throw new UserNotFoundException(String.format("Пользователь с именем %s не найден", username));
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public boolean userExistsByUUID(String uuid) {
        return userRepository.existsUserByUUID(uuid);
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .getUserByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(
                            String.format("Пользователь с имененм %s не найден", username)
                    );
                });
    }
}
