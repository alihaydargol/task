package com.producter.task.service;

import com.producter.task.errorhandler.exception.BasketballException;
import com.producter.task.errorhandler.exception.ExceptionType;
import com.producter.task.model.BasketballPlayer;
import com.producter.task.model.BasketballPlayerInput;
import com.producter.task.repository.BasketballPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketBallPlayerService {

    private final BasketballPlayerRepository basketballPlayerRepository;

    @Autowired
    public BasketBallPlayerService(BasketballPlayerRepository basketballPlayerRepository) {
        this.basketballPlayerRepository = basketballPlayerRepository;
    }

    public Page<BasketballPlayer> findAll(int page, int size) {
        return basketballPlayerRepository.findAll(PageRequest.of(page,size));
    }

    public BasketballPlayer addBasketBallPlayer(BasketballPlayerInput player) {
        if (player == null)
            throw new BasketballException(ExceptionType.BASKETBALL_PLAYER_NULL);

        if (basketballPlayerRepository.count() >= 12)
            throw new BasketballException(ExceptionType.TEAM_SIZE_EXCEED);

        return basketballPlayerRepository.save(player.toEntity());
    }

    public BasketballPlayer removeBasketballPlayer(Long id) {
        Optional<BasketballPlayer> player = basketballPlayerRepository.findById(id);

        if (player.isEmpty())
            throw new BasketballException(ExceptionType.BASKETBALL_PLAYER_NOT_FOUND);

        basketballPlayerRepository.deleteById(id);

        return player.get();
    }

    public BasketballPlayer edit(Long id, String name) {
        var x = basketballPlayerRepository.findById(id);
        var y = x.orElseThrow();
        y.setName(name);

        return basketballPlayerRepository.save(y);
    }
}
