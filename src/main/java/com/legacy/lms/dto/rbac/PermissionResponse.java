package com.legacy.lms.dto.rbac;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PermissionResponse {
    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;
}
