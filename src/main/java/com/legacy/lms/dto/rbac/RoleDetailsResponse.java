package com.legacy.lms.dto.rbac;

import com.legacy.lms.dto.DetailsResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDetailsResponse extends DetailsResponse {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private List<PermissionResponse> permissions;


}
