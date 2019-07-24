package com.acme.games.rockpaperscissors.main.controller;

import com.acme.games.rockpaperscissors.main.Paths;
import com.acme.games.rockpaperscissors.main.domain.Round;
import com.acme.games.rockpaperscissors.main.entities.Game;
import com.acme.games.rockpaperscissors.main.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RockPaperScissorsController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = Paths.API_PATH + "/game/{id}", method = RequestMethod.GET)
    public List<Round> statistics(@PathVariable("id") Long id) {
        return gameService.rounds(id);
    }

    @RequestMapping(value = Paths.API_PATH + "/game", method = RequestMethod.POST)
    public Game create() {
        return gameService.create();
    }
}
