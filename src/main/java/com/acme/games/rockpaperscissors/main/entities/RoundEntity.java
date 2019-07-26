package com.acme.games.rockpaperscissors.main.entities;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Winner;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class RoundEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_move")
    @Enumerated(EnumType.STRING)
    private Move userMove;

    @Enumerated(EnumType.STRING)
    @Column(name = "system_move")
    private Move systemMove;

    @Enumerated(EnumType.STRING)
    private Winner winner;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private Set<GameEntity> game;

    public RoundEntity() {
    }

    public RoundEntity(Move userMove, Move systemMove, Winner winner) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GameEntity> getGame() {
        return game;
    }

    public void setGame(Set<GameEntity> game) {
        this.game = game;
    }
}
