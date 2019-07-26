package com.acme.games.rockpaperscissors.main.service;


import com.acme.games.rockpaperscissors.main.domain.Game;
import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.entities.GameEntity;
import com.acme.games.rockpaperscissors.main.entities.RoundEntity;
import com.acme.games.rockpaperscissors.main.repository.RockPaperScissorsRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("RockPaperScissorsService")
public class RockPaperScissorsService implements GameService {

    @Autowired
    private RockPaperScissorsRepository repository;

    @Autowired
    private JudgeJosephDreddService judgeJosephDreddService;

    @Override
    public Game create(String userId, Move userMove) {
        Move systemMove = Move.values()[new Random().nextInt(Move.values().length)];
        RoundEntity roundEntity = judgeJosephDreddService.Round(userMove, systemMove);

        GameEntity game = new GameEntity();
        game.setUserId(userId);
        game.setRound(roundEntity);
        GameEntity savedGame = repository.save(game);

        return toGame(savedGame);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll().stream().map(this::toGame).collect(Collectors.toList());
    }

    @Nullable
    @Override
    public List<Game> findByUserId(@NotNull String userId) {
        return repository.findByUserId(userId).stream().map(this::toGame).collect(Collectors.toList());
    }

    @NotNull
    private Game toGame(GameEntity savedGame) {
        Game result = new Game();
        result.setId(savedGame.getId());
        result.setUserId(savedGame.getUserId());
        result.setSystemMove(savedGame.getRound().getSystemMove());
        result.setUserMove(savedGame.getRound().getUserMove());
        result.setWinner(savedGame.getRound().getWinner());
        return result;
    }
}
