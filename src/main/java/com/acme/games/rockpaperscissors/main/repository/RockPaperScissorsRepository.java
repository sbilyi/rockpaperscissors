package com.acme.games.rockpaperscissors.main.repository;

import com.acme.games.rockpaperscissors.main.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RockPaperScissorsRepository extends JpaRepository<Game, Long> {
}
