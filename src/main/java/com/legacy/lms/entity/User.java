package com.legacy.lms.entity;


import com.legacy.lms.entity.rbac.Permission;
import com.legacy.lms.entity.rbac.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
//    @Pattern(regexp = Constants.PASSWORD_VALIDATION_REGEXP, message = "password must contains at least 8 characters, 1 number, and 1 special character")
    protected String password;

    @NotBlank(message = "Username must not be blank or null")
    @Column(nullable = false, unique = true)
    protected String username;

    @Getter
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Where(clause = "deleted_at is null")
    protected Set<Role> roles = new HashSet<>();



    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean can(String permissionCode) {
        return getAuthorities()
                .stream()
                .anyMatch(p -> p.getCode().equals(permissionCode));
    }


    @Override
    public Collection<Permission> getAuthorities() {
        var authorities = new HashSet<Permission>();

        roles.forEach(role -> authorities.addAll(role.getPermissions()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
