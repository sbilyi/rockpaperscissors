package com.acme.games.rockpaperscissors.main.repository;

import com.acme.games.rockpaperscissors.main.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RockPaperScissorsRepository extends JpaRepository<GameEntity, Long> {
    List<GameEntity> findByUserId(String userId);
}
