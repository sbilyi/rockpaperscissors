package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.entities.Game;

public interface GameService {

    Game create();

    Game move(Long id, Move move);

    Game find(Long id);
}
