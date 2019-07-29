package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.controller.GamesStats;
import com.acme.games.rockpaperscissors.main.domain.Game;
import com.acme.games.rockpaperscissors.main.domain.Move;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface GameService {

    Game
    create(String userId, Move rock);

    Long count();

    List<Game> findAll();

    @Nullable
    GamesStats loadStats(@NotNull String userId);
}
