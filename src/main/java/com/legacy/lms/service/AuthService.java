package com.legacy.lms.service;

import com.legacy.lms.dto.auth.LoginRequest;
import com.legacy.lms.entity.User;
import com.legacy.lms.repository.UserRepository;
import com.legacy.lms.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public String login(LoginRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = (User) auth.getPrincipal();

        return jwtTokenUtil.generateAccessToken(user);
    }

}
