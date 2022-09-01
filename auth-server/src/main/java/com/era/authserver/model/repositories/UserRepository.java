package com.era.authserver.model.repositories;

import com.era.authserver.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //TODO:Сделать pagination для получения списка пользователей

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByUUID(String uuid);

    void deleteUserByUsername(String username);

    void deleteUserByUUID(String uuid);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByUUID(String uuid);

}
