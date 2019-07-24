package com.acme.games.rockpaperscissors.main.domain;

public class Round {
    private Move userMove;
    private Move systemMove;

    public Round() {
    }

    public Round(Move userMove, Move systemMove) {
        this.userMove = userMove;
        this.systemMove = systemMove;
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
}
