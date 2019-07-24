package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.entities.Game;
import com.acme.games.rockpaperscissors.main.domain.Round;

import java.util.List;

public interface GameService {

    List<Round> rounds(Long id);

    Game create();
}
