package com.era.apiuser.model.services.interfaces;

import com.era.apiuser.model.entities.User;
import com.era.apiuser.model.exceptions.UserAlreadyExistsException;
import com.era.apiuser.model.exceptions.UserNotFoundException;
import com.era.apiuser.model.requests.UserRegistrationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException;

    List<User> getAll();

    Page<User> getUserByParams(String uuid, String username, String email, Pageable pageable);

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
