package com.acme.games.rockpaperscissors.main;

import com.acme.games.rockpaperscissors.main.domain.Round;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Game {
    List<Round> rounds;

    @Id
    @GeneratedValue

    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
