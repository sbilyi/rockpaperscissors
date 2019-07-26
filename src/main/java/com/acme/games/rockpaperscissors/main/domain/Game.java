package com.acme.games.rockpaperscissors.main.domain;

public class Game {
    private long id;
    private String userId;
    private Move userMove;
    private Move systemMove;
    private Winner winner;

    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Move getUserMove() {
        return userMove;
    }

    public void setUserMove(Move userMove) {
        this.userMove = userMove;
    }

    public Move getSystemMove() {
        return systemMove;
    }

    public void setSystemMove(Move systemMove) {
        this.systemMove = systemMove;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }
}
