package com.era.apiuser.model.repositories;

import com.era.apiuser.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByUUID(String uuid);

    @Query(value = "SELECT * FROM user_api.users user WHERE user.uuid LIKE CONCAT('%',:uuid,'%') and user.username LIKE CONCAT('%',:username,'%') and user.email LIKE CONCAT('%',:email,'%')",
            nativeQuery = true)
    Page<User> getUserByUUIDOrUsernameOrEmail(String uuid, String username, String email, Pageable pageable);

    void deleteUserByUsername(String username);

    void deleteUserByUUID(String uuid);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByUUID(String uuid);

}
