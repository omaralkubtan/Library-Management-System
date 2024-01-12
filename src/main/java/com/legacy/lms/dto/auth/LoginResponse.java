package com.legacy.lms.dto.auth;


import com.legacy.lms.dto.user.UserDetailsResponse;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class LoginResponse {
    @NotNull
    private UserDetailsResponse user;

    @NotNull
    private String token;
}