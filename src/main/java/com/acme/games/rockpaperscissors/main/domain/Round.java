package com.acme.games.rockpaperscissors.main.domain;


import com.acme.games.rockpaperscissors.main.service.JudgeJosephDreddService;

public class Round {
    private Move userMove;
    private Move systemMove;
    private Winner winner;

    public Round(Move userMove, Move systemMove) {
        this.userMove = userMove;
        this.systemMove = systemMove;
        this.winner = JudgeJosephDreddService.judge(userMove, systemMove);
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
