package com.producter.task.service;

import com.producter.task.errorhandler.exception.BasketballException;
import com.producter.task.errorhandler.exception.ExceptionType;
import com.producter.task.model.BasketballPlayer;
import com.producter.task.model.BasketballPlayerInput;
import com.producter.task.model.Position;
import com.producter.task.repository.BasketballPlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasketBallPlayerServiceTest {


    private BasketballPlayerRepository basketballPlayerRepository;
    private BasketBallPlayerService basketBallPlayerService;

    @BeforeEach
    void setUp() {
        basketballPlayerRepository  = mock(BasketballPlayerRepository.class);
        basketBallPlayerService = new BasketBallPlayerService(basketballPlayerRepository);
    }

    @Test
    void findAll() {
        List<BasketballPlayer> expected = List.of(new BasketballPlayer("abc", "def", Position.CENTER), new BasketballPlayer("qwe", "ert", Position.POINT_GUARD));
        when(basketballPlayerRepository.findAll()).thenReturn(expected);

        //List<BasketballPlayer> actual = basketBallPlayerService.findAll();
        //assertEquals(expected, actual);
    }

    @Test
    void addBasketBallPlayer() {
        BasketballPlayer expected = new BasketballPlayer("abc", "def", Position.CENTER);
        BasketballPlayerInput basketballPlayerInput = new BasketballPlayerInput();
        when(basketballPlayerRepository.save(basketballPlayerInput.toEntity())).thenReturn(expected);

        BasketballPlayer actual = basketBallPlayerService.addBasketBallPlayer(basketballPlayerInput);
        assertEquals(expected, actual);
    }

    @Test
    void addBasketBallPlayerWhenSizeExceed() {
        when(basketballPlayerRepository.count()).thenReturn(12L);

        BasketballException exception = assertThrows(BasketballException.class,
                () -> basketBallPlayerService.addBasketBallPlayer(new BasketballPlayerInput()));
        assertEquals(exception.getExceptionType(), ExceptionType.TEAM_SIZE_EXCEED);
    }

    @Test
    void addBasketBallPlayerWhenPlayerNull() {
        when(basketballPlayerRepository.count()).thenReturn(12L);

        BasketballException exception = assertThrows(BasketballException.class,
                () -> basketBallPlayerService.addBasketBallPlayer(null));
        assertEquals(exception.getExceptionType(), ExceptionType.BASKETBALL_PLAYER_NULL);
    }

    @Test
    void removeBasketballPlayer() {
        Long id = 3L;
        when(basketballPlayerRepository.findById(id))
                .thenReturn(Optional.of(new BasketballPlayer("def", "fed", Position.CENTER)));

        BasketballPlayer actual = basketBallPlayerService.removeBasketballPlayer(id);
        assertNotNull(actual);
    }

    @Test
    void removeBasketballPlayerWhenPlayerNotFound() {
        Long id = 3L;
        when(basketballPlayerRepository.findById(id))
                .thenReturn(Optional.empty());

        BasketballException exception = assertThrows(BasketballException.class,
                () -> basketBallPlayerService.removeBasketballPlayer(id));
        assertEquals(exception.getExceptionType(), ExceptionType.BASKETBALL_PLAYER_NOT_FOUND);
    }
}