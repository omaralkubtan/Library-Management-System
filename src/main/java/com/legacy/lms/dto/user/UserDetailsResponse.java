package com.legacy.lms.dto.user;

import com.legacy.lms.dto.rbac.PermissionResponse;
import com.legacy.lms.dto.rbac.RoleListResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailsResponse {

    private Long id;

    private String name;

    private String address;

    private List<RoleListResponse> roles;

    @JsonProperty("permissions")
    private List<PermissionResponse> authorities;

}
