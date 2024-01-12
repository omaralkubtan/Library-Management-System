package com.legacy.lms.dto.rbac;

import com.legacy.lms.dto.ListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListResponse extends ListResponse {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
