package com.era.authserver.model.repositories;

import com.era.authserver.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByName(String name);

    void deleteRoleById(Long id);

    void deleteRoleByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);

}
