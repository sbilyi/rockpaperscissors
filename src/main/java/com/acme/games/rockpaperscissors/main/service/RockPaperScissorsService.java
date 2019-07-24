package com.acme.games.rockpaperscissors.main.service;


import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Round;
import com.acme.games.rockpaperscissors.main.entities.Game;
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("RockPaperScissorsService")
public class RockPaperScissorsService implements GameService {

    @Autowired
    private RockPaperScissorsRepository repository;

    @Override
    public Game create() {
        Game game = new Game();
        return repository.save(game);
    }

    @Override
    public Game move(Long id, Move userMove) {
        Game game = repository.findById(id).orElse(new Game());
        Move systemMove = Move.values()[new Random().nextInt(Move.values().length)];
        game.getRounds().add(new Round(userMove, systemMove));
        repository.save(game);
        return game;
    }

    @Override
    public Game find(Long id) {
        return repository.findById(id).get();
    }
}
