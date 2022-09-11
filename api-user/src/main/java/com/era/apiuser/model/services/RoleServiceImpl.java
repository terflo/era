package com.era.apiuser.model.services;

import com.era.apiuser.model.entities.Role;
import com.era.apiuser.model.exceptions.RoleAlreadyExistsException;
import com.era.apiuser.model.exceptions.RoleNotFoundException;
import com.era.apiuser.model.repositories.RoleRepository;
import com.era.apiuser.model.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(String roleName) throws RoleAlreadyExistsException {
        if(this.roleRepository.existsByName(roleName))
            throw new RoleAlreadyExistsException(String.format("Роль с именем %s уже существует", roleName));
        return this.roleRepository.save(
                        Role
                                .builder()
                                .name(roleName)
                                .build()
                );
    }

    @Override
    public Role getRoleByName(String name) throws RoleNotFoundException {
        return this.roleRepository.getRoleByName(name).orElseThrow(() -> {
            throw new RoleNotFoundException(String.format("Роли с именем %s не существует", name));
        });
    }

    @Override
    public Role getRoleById(Long id) throws RoleNotFoundException {
        return this.roleRepository.getRoleById(id).orElseThrow(() -> {
            throw new RoleNotFoundException(String.format("Роли с id %s не существует", id));
        });
    }

    @Override
    public Role updateRole(Role role) throws RoleNotFoundException {
        this.roleRepository.getRoleById(role.getId()).orElseThrow(() -> {
            throw new RoleNotFoundException(String.format("Роли с id %s не существует", role.getId()));
        });
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) throws RoleNotFoundException {
        if(!this.roleRepository.existsByName(role.getName()))
            throw new RoleNotFoundException(String.format("Роли с именем %s не существует", role.getName()));
        this.roleRepository.delete(role);
    }

    @Override
    public void deleteRoleById(Long id) throws RoleNotFoundException {
        if(!this.roleRepository.existsById(id))
            throw new RoleNotFoundException(String.format("Роли с id %s не существует", id));
        this.roleRepository.deleteRoleById(id);
    }

    @Override
    public void deleteRoleByName(String name) throws RoleNotFoundException {
        if(!this.roleRepository.existsByName(name))
            throw new RoleNotFoundException(String.format("Роли с именем %s не существует", name));
        this.roleRepository.deleteRoleByName(name);
    }

    @Override
    public Set<Role> getAll() {
        return new HashSet<>(this.roleRepository.findAll());
    }
}
