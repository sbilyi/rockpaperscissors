package com.acme.games.rockpaperscissors.main.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String userId;

    @ManyToOne(targetEntity = RoundEntity.class)
    @JoinColumn
    private RoundEntity round;

    public GameEntity() {
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

    public RoundEntity getRound() {
        return round;
    }

    public void setRound(RoundEntity round) {
        if (round != null) {
            this.round = round;
        }
    }
}
