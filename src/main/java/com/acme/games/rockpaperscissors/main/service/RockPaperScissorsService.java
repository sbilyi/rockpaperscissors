package com.acme.games.rockpaperscissors.main.service;


import com.acme.games.rockpaperscissors.main.entities.Game;
import com.acme.games.rockpaperscissors.main.domain.Round;
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RockPaperScissorsService")
public class RockPaperScissorsService implements GameService {

    @Autowired
    private RockPaperScissorsRepository repository;

    @Override
    public List<Round> rounds(Long id) {
        return repository.findById(id).orElse(new Game()).getRounds();
    }

    @Override
    public Game create() {
        Game game = new Game();
        return repository.save(game);
    }
}
