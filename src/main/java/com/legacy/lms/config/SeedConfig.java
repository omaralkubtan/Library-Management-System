package com.legacy.lms.config;

import com.legacy.lms.entity.rbac.Permission;
import com.legacy.lms.entity.rbac.Role;
import com.legacy.lms.rbac.Permissions;
import com.legacy.lms.repository.UserRepository;
import com.legacy.lms.repository.security.PermissionRepository;
import com.legacy.lms.repository.security.RoleRepository;
import com.legacy.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class SeedConfig {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedAdminUser();
    }

    private void seedAdminUser() {
        if (permissionRepository.count() >= Permissions.class.getDeclaredFields().length) return;

        permissionRepository.deleteAll();

        var permissions = Arrays.stream(Permissions.class.getDeclaredFields()).map(p -> {
            var permission = new Permission();
            permission.setCode(p.getName().toLowerCase().replace("_", "-"));
            return permission;
        }).collect(Collectors.toList());

        permissionRepository.saveAll(permissions);

        var superRole = new Role();
        superRole.setName("Super Role");
        superRole.getPermissions().addAll(permissions);

        roleRepository.save(superRole);

        if (userRepository.findByUsername("admin") != null) return;

        var superUser = new User();

        superUser.setPassword(passwordEncoder.encode("password"));
        superUser.setUsername("admin");
        userRepository.save(superUser);

        superUser.getRoles().add(superRole);

        userRepository.save(superUser);
    }
}
