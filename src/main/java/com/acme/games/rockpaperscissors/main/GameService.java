package com.acme.games.rockpaperscissors.main;

import com.acme.games.rockpaperscissors.main.domain.Round;

import java.util.List;

public interface GameService {

    List<Round> rounds(Long id);

    Game create();
}
