package com.legacy.lms.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLightWithoutPermissionResponse {

    @NotNull
    private Long id;

    @NotNull
    private String name;
}
