package com.acme.games.rockpaperscissors.main.domain;

public class Round {

    private Move userMove;
    private Move systemMove;
    private Winner winner;

    public Round() {
    }

    public Round(Move userMove, Move systemMove, Winner winner) {
        this.userMove = userMove;
        this.systemMove = systemMove;
        this.winner = winner;
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
