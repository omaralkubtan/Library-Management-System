package com.legacy.lms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User - Users")
@RestController
@RequestMapping("user")
@Validated
public class UserController extends ApiController {
//    @Operation(
//            summary = "Get All Users",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @GetMapping("/")
//    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_USERS + AUTH_SUFFIX)
//    public PaginationResult<UserListResponse> getAllUsers(
//            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
//            @PositiveOrZero
//                    Integer page,
//
//            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
//            @Positive
//                    Integer pageSize,
//
//            @Parameter(description = "name, code, createdAt, updatedAt, deletedAt")
//            @RequestParam(required = false, defaultValue = "createdAt:desc")
//                    String sort,
//
//            @RequestParam(required = false)
//                    String search,
//
//            @RequestParam(required = false)
//                    Long jobId,
//
//            @RequestParam(required = false)
//                    UserStatus status
//    ) {
//        var users = servicePool.getUserService().getAll(
//                new UserPage(page, pageSize, sort),
//                new UserFilter(search, status, jobId)
//        );
//
//        return users.mapTo(modelMapper, UserListResponse.class);
//    }
//
//    @Operation(
//            summary = "Get All Users (Light)",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @GetMapping("/light")
//    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_USERS_LIGHT + AUTH_SUFFIX)
//    public List<UserLightResponse> getAllUsersLight(
//            @RequestParam(required = false) String search,
//            @RequestParam(required = false) String permission
//    ) {
//        var users = servicePool.getUserService().getAllLight(
//                new UserLightFilter(search, permission)
//        );
//
//        return modelMapper.map(users, new TypeToken<List<UserLightResponse>>() {
//        }.getType());
//    }
//
//    @Operation(
//            summary = "Get User by ID",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @GetMapping("/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.GET_USER_DETAILS + AUTH_SUFFIX)
//    public UserDetailsResponse getUser(@PathVariable Long id) {
//        var user = servicePool.getUserService().get(id);
//        return modelMapper.map(user, UserDetailsResponse.class);
//    }
//
//    @Operation(
//            summary = "Create New User",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PostMapping("/")
//    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_USER + AUTH_SUFFIX)
//    public UserDetailsResponse createUser(@Valid @RequestBody CreateUserRequest request) {
//        var user = servicePool.getUserService().create(request);
//        return modelMapper.map(user, UserDetailsResponse.class);
//    }
//
//    @Operation(
//            summary = "Create New User (Quick)",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PostMapping("/quick")
//    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_USER_QUICK + AUTH_SUFFIX)
//    public UserListResponse createUserQuick(@Valid @RequestBody CreateUserQuickRequest request) {
//        var user = servicePool.getUserService().createQuick(request);
//        return modelMapper.map(user, UserListResponse.class);
//    }
//
//    @Operation(
//            summary = "Update Existing User",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PutMapping("/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_USER + AUTH_SUFFIX)
//    public UserDetailsResponse updateUser(
//            @PathVariable("id") Long id,
//            @Valid @RequestBody UpdateUserRequest request
//    ) {
//        var user = servicePool.getUserService().update(id, request);
//        return modelMapper.map(user, UserDetailsResponse.class);
//    }
//
//    @Operation(
//            summary = "Update Existing User (Quick)",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PutMapping("/{id}/quick")
//    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_USER_QUICK + AUTH_SUFFIX)
//    public UserListResponse updateUserQuick(
//            @PathVariable("id") Long id,
//            @Valid @RequestBody UpdateUserQuickRequest request
//    ) {
//        var user = servicePool.getUserService().updateQuick(id, request);
//        return modelMapper.map(user, UserListResponse.class);
//    }
//
//    @Operation(
//            summary = "Delete Existing User",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @DeleteMapping("/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_USER + AUTH_SUFFIX)
//    public void deleteUser(@PathVariable("id") Long id) {
//        servicePool.getUserService().delete(id);
//    }
//
//    @Operation(
//            summary = "Create New User Contact Info",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PostMapping("/{userId}/contact-info")
//    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_USER_CONTACT_INFO + AUTH_SUFFIX)
//    public UserContactInfoResponse createUserContactInfo(
//            @PathVariable("userId") Long userId,
//            @Valid @RequestBody CreateUserContactInfoRequest request
//    ) {
//        var info = servicePool.getUserContactInfoService().create(userId, request);
//        return modelMapper.map(info, UserContactInfoResponse.class);
//    }
//
//    @Operation(
//            summary = "Update Existing User Contact Info",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PutMapping("/{userId}/contact-info/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_USER_CONTACT_INFO + AUTH_SUFFIX)
//    public UserContactInfoResponse updateUserContactInfo(
//            @PathVariable("userId") Long userId,
//            @PathVariable("id") Long id,
//            @Valid @RequestBody UpdateUserContactInfoRequest request
//    ) {
//        var info = servicePool.getUserContactInfoService().update(userId, id, request);
//        return modelMapper.map(info, UserContactInfoResponse.class);
//    }
//
//    @Operation(
//            summary = "Delete Existing User Contact Info",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @DeleteMapping("/{userId}/contact-info/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_USER_CONTACT_INFO + AUTH_SUFFIX)
//    public void deleteUserContactInfo(
//            @PathVariable("userId") Long userId,
//            @PathVariable("id") Long id
//    ) {
//        servicePool.getUserContactInfoService().delete(userId, id);
//    }
//
//    @Operation(
//            summary = "Create New User Sales Share",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PostMapping("/{userId}/sales-share")
//    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_USER_SALES_SHARE + AUTH_SUFFIX)
//    public UserSalesShareResponse createUserSalesShare(
//            @PathVariable("userId") Long userId,
//            @Valid @RequestBody CreateUserSalesShareRequest request
//    ) {
//        var share = servicePool.getUserSalesShareService().create(userId, request);
//        return modelMapper.map(share, UserSalesShareResponse.class);
//    }
//
//    @Operation(
//            summary = "Update Existing User Sales Share",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @PutMapping("/{userId}/sales-share/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_USER_SALES_SHARE + AUTH_SUFFIX)
//    public UserSalesShareResponse updateUserSalesShare(
//            @PathVariable("userId") Long userId,
//            @PathVariable("id") Long id,
//            @Valid @RequestBody UpdateUserSalesShareRequest request
//    ) {
//        var share = servicePool.getUserSalesShareService().update(userId, id, request);
//        return modelMapper.map(share, UserSalesShareResponse.class);
//    }
//
//    @Operation(
//            summary = "Delete Existing User Sales Share",
//            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
//    )
//    @DeleteMapping("/{userId}/sales-share/{id}")
//    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_USER_SALES_SHARE + AUTH_SUFFIX)
//    public void deleteUserSalesShare(
//            @PathVariable("userId") Long userId,
//            @PathVariable("id") Long id
//    ) {
//        servicePool.getUserSalesShareService().delete(userId, id);
//    }
}
