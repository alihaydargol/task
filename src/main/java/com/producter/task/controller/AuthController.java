package com.producter.task.controller;

import com.producter.task.errorhandler.exception.BasketballException;
import com.producter.task.model.Token;
import com.producter.task.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @MutationMapping
    public Token register(@Argument String username) throws BasketballException {
        return new Token(service.register(username));
    }

    @QueryMapping
    public Token auth(@Argument String username) throws BasketballException {
        return new Token(service.auth(username));
    }
}
