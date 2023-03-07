package com.producter.task.controller;

import com.producter.task.model.BasketballPlayer;
import com.producter.task.model.BasketballPlayerInput;
import com.producter.task.service.BasketBallPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BasketballController {

    private final BasketBallPlayerService basketBallPlayerService;

    @Autowired
    public BasketballController(BasketBallPlayerService basketBallPlayerService) {
        this.basketBallPlayerService = basketBallPlayerService;
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public Page<BasketballPlayer> findAll(@Argument int page, @Argument int size) {
        return basketBallPlayerService.findAll(page, size);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('USER')")
    public BasketballPlayer edit(@Argument Long id, @Argument String name) {
        return basketBallPlayerService.edit(id, name);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('USER')")
    public BasketballPlayer addBasketballPlayer(@Argument BasketballPlayerInput player) {
        return basketBallPlayerService.addBasketBallPlayer(player);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('USER')")
    public BasketballPlayer deleteBasketballPlayer(@Argument Long id) {
        return basketBallPlayerService.removeBasketballPlayer(id);
    }
}
