package com.acme.games.rockpaperscissors.main.repository;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundsRepository extends JpaRepository<RoundEntity, Long> {

    @Query("SELECT r FROM RoundEntity r WHERE r.userMove = ?1 AND r.systemMove = ?2")
    RoundEntity findByUserMoveAndSystemMove(Move userMove, Move systemMove);
}
