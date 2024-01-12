package com.legacy.lms.service;

import com.legacy.lms.dto.rbac.CreateRoleRequest;
import com.legacy.lms.dto.rbac.UpdateRoleRequest;
import com.legacy.lms.entity.rbac.Permission;
import com.legacy.lms.entity.rbac.Role;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.repository.security.PermissionRepository;
import com.legacy.lms.repository.security.RoleRepository;
import com.legacy.lms.util.helper.PaginationResult;
import com.legacy.lms.util.localization.Tokens;
import com.google.common.collect.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleAndPermissionService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public PaginationResult<Role> getAllRoles(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Role> rolesPage = roleRepository.findAll(pageable);

        return new PaginationResult<>(rolesPage);
    }

    public Role getRole(Long id) {
        var role = roleRepository.findById(id);

        if (role.isEmpty() || role.get().getIsDeleted()) {
            throw new EntityNotFoundException(Tokens.M_ROLE_NOT_FOUND);
        }

        return role.get();
    }

    @Transactional
    public Role createRole(CreateRoleRequest request) {

        var role = new Role();
        role.setName(request.getName());
        roleRepository.save(role);

        var permissions = getPermissionsByIdsAndValidate(request.getPermissions());
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }


    @Transactional
    public Role updateRole(Long id, UpdateRoleRequest request) {
        var role = getRole(id);
        role.refresh();

        var permissions = getPermissionsByIdsAndValidate(request.getPermissions());
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }


    @Transactional
    public void deleteRole(Long id) {
        var role = getRole(id);

        if (role.getDeletedAt() != null) {
            throw new BadRequestException(Tokens.M_ROLE_ALREADY_DELETED);
        }

        role.delete();

        roleRepository.save(role);
    }

    public List<Permission> getAllPermissions() {
        return Arrays
                .stream(Iterators.toArray(permissionRepository.findAll().iterator(), Permission.class))
                .collect(Collectors.toList());
    }

    private Set<Permission> getPermissionsByIdsAndValidate(Set<Long> ids) {
        var permissions = permissionRepository.findAllById(ids);
        return Arrays
                .stream(Iterators.toArray(permissions.iterator(), Permission.class))
                .collect(Collectors.toSet());
    }
}
