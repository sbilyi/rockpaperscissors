package com.acme.games.rockpaperscissors.main.controller;

import com.acme.games.rockpaperscissors.main.Paths;
import com.acme.games.rockpaperscissors.main.domain.Game;
import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RockPaperScissorsController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = Paths.API_PATH + "/game/{userId}", method = RequestMethod.GET)
    public GamesStats statistics(@PathVariable("userId") String userId) {
        return gameService.findByUserId(userId);
    }

    @RequestMapping(value = Paths.API_PATH + "/game/{userId}/{move}", method = RequestMethod.POST)
    public Game newGame(@PathVariable("userId") String userId, @PathVariable("move") String move) {
        return gameService.create(userId, Move.valueOf(move.toUpperCase()));
    }
}
