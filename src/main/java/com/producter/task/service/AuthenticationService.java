package com.producter.task.service;

import com.producter.task.util.JwtUtil;
import com.producter.task.errorhandler.exception.BasketballException;
import com.producter.task.errorhandler.exception.ExceptionType;
import com.producter.task.model.user.Role;
import com.producter.task.model.user.User;
import com.producter.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    public String register(String username) {
        User user = User.builder()
                .username(username)
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return JwtUtil.generateToken(user);
    }

    public String auth(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            throw new BasketballException(ExceptionType.INVALID_CREDENTIALS);

        return JwtUtil.generateToken(user.get());
    }

}
