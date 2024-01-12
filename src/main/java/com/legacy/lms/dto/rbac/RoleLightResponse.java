package com.legacy.lms.dto.rbac;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleLightResponse {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
