package com.legacy.lms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {

    @NotBlank(message = "Please enter your username")
    private String username;

    @NotBlank(message = "Please enter your password")
    private String password;
}