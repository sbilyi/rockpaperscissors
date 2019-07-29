package com.acme.games.rockpaperscissors.main.controller;

import com.acme.games.rockpaperscissors.main.domain.Game;
import com.acme.games.rockpaperscissors.main.domain.Winner;

import java.util.List;

public class GamesStats {

    private final int gamesNumber;
    private final float userWins;
    private final float systemWins;
    private final float noneWins;
    private final List<Game> games;

    public GamesStats(List<Game> games) {
        this.games = games;
        int byUser = 0;
        int bySystem = 0;

        userWins = games.size() == 0 ? 0 : (float)games.stream().filter(e -> Winner.USER.equals(e.getWinner())).count() / games.size();
        systemWins = games.size() == 0 ? 0 : (float)games.stream().filter(e -> Winner.SYSTEM.equals(e.getWinner())).count() / games.size();
        noneWins = games.size() == 0 ? 0 : 1 - userWins - systemWins;
        this.gamesNumber = games.size();
    }

    public int getGamesNumber() {
        return gamesNumber;
    }

    public float getUserWins() {
        return userWins;
    }

    public float getSystemWins() {
        return systemWins;
    }

    public List<Game> getGames() {
        return games;
    }

    public float getNoneWins() {
        return noneWins;
    }
}
