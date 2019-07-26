package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.entities.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundsRepository extends JpaRepository<Round,Long> {
}
