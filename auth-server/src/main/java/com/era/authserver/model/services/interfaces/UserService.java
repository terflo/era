package com.era.authserver.model.services.interfaces;

import com.era.authserver.model.entities.User;
import com.era.authserver.model.exceptions.UserAlreadyExistsException;
import com.era.authserver.model.exceptions.UserNotFoundException;
import com.era.authserver.model.requests.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException;

    List<User> getAll();

    User getUserByUUID(String uuid) throws UserNotFoundException;

    User getUserByUsername(String username) throws UserNotFoundException;

    void updateUser(User user) throws UserNotFoundException, UserAlreadyExistsException;

    void deleteUser(User user) throws UserNotFoundException;

    void deleteUserByUUID(String uuid) throws UserNotFoundException;

    void deleteUserByUsername(String username) throws UserNotFoundException;

    boolean userExistsByUUID(String uuid);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);


}
