package com.acme.games.rockpaperscissors.main.entities;

import com.acme.games.rockpaperscissors.main.domain.Round;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    private List<Round> rounds;

    @Id
    @GeneratedValue
    private long id;

    public Game() {
        rounds = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
