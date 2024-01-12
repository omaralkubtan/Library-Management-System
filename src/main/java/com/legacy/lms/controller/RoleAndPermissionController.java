package com.legacy.lms.controller;


import com.legacy.lms.rbac.Permissions;
import com.legacy.lms.service.RoleAndPermissionService;
import com.legacy.lms.util.helper.Constants;

import com.legacy.lms.util.helper.PaginationResult;
import com.legacy.lms.dto.rbac.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Tag(name = "Roles & Permissions")
@RestController
@Validated
public class RoleAndPermissionController extends ApiController {

    @Autowired
    private RoleAndPermissionService roleAndPermissionService;

    @Operation(
            summary = "Get All Roles",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/role")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_ROLES + AUTH_SUFFIX)
    public PaginationResult<RoleListResponse> getAllRoles(
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
            @PositiveOrZero
                    Integer page,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
            @Positive
                    Integer pageSize
    )
    {
        var roles = roleAndPermissionService.getAllRoles(page, pageSize);
        return roles.mapTo(modelMapper, RoleListResponse.class);
    }


    @Operation(
            summary = "Get All Permissions",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/permission")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_PERMISSIONS + AUTH_SUFFIX)
    public List<PermissionResponse> getAllPermissions() {
        var permissions = roleAndPermissionService.getAllPermissions();
        return modelMapper.map(permissions, new TypeToken<List<PermissionResponse>>() {
        }.getType());
    }

    @Operation(
            summary = "Get Role by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/role/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ROLE_DETAILS + AUTH_SUFFIX)
    public RoleDetailsResponse getRole(@PathVariable Long id) {
        var role = roleAndPermissionService.getRole(id);
        return modelMapper.map(role, RoleDetailsResponse.class);
    }

    @Operation(
            summary = "Create New Role",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/role")
    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_ROLE + AUTH_SUFFIX)
    public RoleDetailsResponse createRole(@Valid @RequestBody CreateRoleRequest request) {
        var role = roleAndPermissionService.createRole(request);
        return modelMapper.map(role, RoleDetailsResponse.class);
    }

    @Operation(
            summary = "Update Existing Role",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/role/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_ROLE + AUTH_SUFFIX)
    public RoleDetailsResponse updateRole(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateRoleRequest request
    ) {
        var role = roleAndPermissionService.updateRole(id, request);
        return modelMapper.map(role, RoleDetailsResponse.class);
    }


    @Operation(
            summary = "Delete Existing Role",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/role/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_ROLE + AUTH_SUFFIX)
    public void deleteRole(@PathVariable("id") Long id) {
        roleAndPermissionService.deleteRole(id);
    }
}
