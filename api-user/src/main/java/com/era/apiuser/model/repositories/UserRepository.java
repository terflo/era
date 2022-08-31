package com.era.apiuser.model.repositories;

import com.era.apiuser.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //TODO:Сделать pagination для получения списка пользователей

    Optional<User> getUserByUsername(String username);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByUUID(String uuid);

}
