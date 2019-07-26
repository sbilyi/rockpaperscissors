package com.acme.games.rockpaperscissors.main.entities;

import com.acme.games.rockpaperscissors.main.domain.Move;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class MovePair implements Serializable {

    @Column(name = "user_move")
    @Enumerated(EnumType.STRING)
    private Move userMove;

    @Enumerated(EnumType.STRING)
    @Column(name = "system_move")
    private Move systemMove;

    public MovePair() {
    }

    public MovePair(Move userMove, Move systemMove) {
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
