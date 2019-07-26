package com.acme.games.rockpaperscissors.main.entities;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Winner;

import javax.persistence.*;

@Entity
@Table(name = "rounds")
public class Round {

    @EmbeddedId
    private MovePair moves;

    @Enumerated(EnumType.STRING)
    private Winner winner;

    public Round() {
    }

    public Round(Move userMove, Move systemMove, Winner winner) {
        this.moves = new MovePair(userMove, systemMove);
        this.winner = winner;
    }

    public Move getUserMove() {
        return moves.getUserMove();
    }

    public void setUserMove(Move userMove) {
        this.moves.setUserMove(userMove);
    }

    public Move getSystemMove() {
        return moves.getSystemMove();
    }

    public void setSystemMove(Move systemMove) {
        this.moves.setSystemMove(systemMove);
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

}
