package com.producter.task.controller;

import com.producter.task.errorhandler.exception.BasketballException;
import com.producter.task.model.rest.AuthRequest;
import com.producter.task.model.rest.AuthResponse;
import com.producter.task.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) throws BasketballException {
        return new AuthResponse(service.register(request.getUsername()));
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) throws BasketballException {
        return new AuthResponse(service.auth(request.getUsername()));
    }
}
