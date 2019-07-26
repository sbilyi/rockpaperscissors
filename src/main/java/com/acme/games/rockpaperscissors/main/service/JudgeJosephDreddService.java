package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Winner;
import com.acme.games.rockpaperscissors.main.entities.RoundEntity;
import com.acme.games.rockpaperscissors.main.repository.RoundsRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This method could be injected as {@link org.springframework.beans.factory.annotation.Autowired}
 * service, but there is no reason to do extra memory usage
 * when the game rules are seems to not be changed in time.
 * <p>
 * Rock Paper Scissors regular matrix are:
 * </p>
 *
 * <table>
 *     <tr><td align="center">System\User</td><td align="center">Rock</td><td align="center">Paper</td><td align="center">Scissors</td></tr>
 *     <tr><td align="center">Rock</td><td align="center">=</td><td align="center">USER</td><td align="center">SYSTEM</td></tr>
 *     <tr><td align="center">Paper</td><td align="center">SYSTEM</td><td align="center">=</td><td align="center">USER</td></tr>
 *     <tr><td align="center">Scissors</td><td align="center">USER</td><td align="center">SYSTEM</td><td align="center">=</td></tr>
 * </table>
 */
@Service
@Scope
public class JudgeJosephDreddService {

    @Autowired
    private RoundsRepository repository;

    private Map<ImmutablePair<Move, Move>, Winner> winnersPredefinedMap = new HashMap<>();

    public JudgeJosephDreddService() {
    }

    public Winner judge(Move userMove, Move systemMove) {
        if (Objects.nonNull(winnersPredefinedMap) || winnersPredefinedMap.isEmpty()) {
            winnersPredefinedMap = loadRoundMap();
        }
        return winnersPredefinedMap.get(new ImmutablePair<>(userMove, systemMove));
    }

    private Map<ImmutablePair<Move, Move>, Winner> loadRoundMap() {
        Map<ImmutablePair<Move, Move>, Winner> roundMap = new HashMap();
        List<RoundEntity> roundEntities = repository.findAll();
        roundEntities.forEach(r -> roundMap.put(new ImmutablePair<>(r.getUserMove(), r.getSystemMove()), r.getWinner()));
        return roundMap;
    }

    public RoundEntity Round(Move userMove, Move systemMove) {
        return repository.findByUserMoveAndSystemMove(userMove, systemMove);
    }

}