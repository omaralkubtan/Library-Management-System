package com.legacy.lms.dto.rbac;

import com.legacy.lms.util.localization.Tokens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateRoleRequest {

    @NotBlank(message = "Please enter the Role name")
    private String name;

    @NotEmpty(message = Tokens.V_NOT_EMPTY)
    private Set<Long> permissions;
}
