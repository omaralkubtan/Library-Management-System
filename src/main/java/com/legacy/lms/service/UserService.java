package com.legacy.lms.service;

import com.legacy.lms.entity.User;
import com.legacy.lms.repository.UserRepository;

import com.legacy.lms.util.localization.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User get(Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty() || user.get().getIsDeleted()) {
            throw new EntityNotFoundException(Tokens.M_USER_NOT_FOUND);
        }

        return user.get();
    }
}
