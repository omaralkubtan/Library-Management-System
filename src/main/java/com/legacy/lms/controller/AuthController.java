package com.legacy.lms.controller;

import com.legacy.lms.dto.auth.LoginRequest;
import com.legacy.lms.dto.auth.LoginResponse;
import com.legacy.lms.dto.user.UserDetailsResponse;
import com.legacy.lms.service.AuthService;
import com.legacy.lms.service.UserService;
import com.legacy.lms.util.jwt.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Tag(name = "Helper - Auth")
@RestController
@RequestMapping("auth")
public class AuthController extends ApiController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Operation(
            summary = "Login",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var token = authService.login(request);

        var user = userService.get(jwtTokenUtil.getUserId(token));

        var response = new LoginResponse();

        response.setUser(modelMapper.map(user, UserDetailsResponse.class));
        response.setToken(token);

        return response;
    }


    @Operation(
            summary = "Logout",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/logout")
    public void logout() {
        // ..
    }
}
