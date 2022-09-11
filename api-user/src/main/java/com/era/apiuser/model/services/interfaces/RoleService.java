package com.era.apiuser.model.services.interfaces;

import com.era.apiuser.model.entities.Role;
import com.era.apiuser.model.exceptions.RoleAlreadyExistsException;
import com.era.apiuser.model.exceptions.RoleNotFoundException;

import java.util.Set;

public interface RoleService {

    Role saveRole(String roleName) throws RoleAlreadyExistsException;

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role getRoleById(Long id) throws RoleNotFoundException;

    Set<Role> getAll();

    Role updateRole(Role role) throws RoleNotFoundException;

    void deleteRole(Role role) throws RoleNotFoundException;

    void deleteRoleById(Long id) throws RoleNotFoundException;

    void deleteRoleByName(String name) throws RoleNotFoundException;

}
