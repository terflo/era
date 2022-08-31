package com.era.apiuser.model.services;

import com.era.apiuser.model.entities.User;
import com.era.apiuser.model.exceptions.UserAlreadyExistsException;
import com.era.apiuser.model.exceptions.UserNotFoundException;
import com.era.apiuser.model.repositories.UserRepository;
import com.era.apiuser.model.requests.UserRegistrationRequest;
import com.era.apiuser.model.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUUID(String uuid) throws UserNotFoundException {
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException, UserAlreadyExistsException {

    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException {

    }

    @Override
    public void deleteUserByUUID(String uuid) throws UserNotFoundException {

    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {

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
}
