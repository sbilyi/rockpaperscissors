package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Winner;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

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
public class JudgeJosephDreddService {

    static final Map<ImmutablePair<Move, Move>, Winner> winnersPredefinedMap = new HashMap<>();

    public JudgeJosephDreddService() {
    }

    public static Winner judge(Move userMove, Move systemMove) {
        return winnersPredefinedMap.get(new ImmutablePair<>(userMove, systemMove));
    }

    static {
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.ROCK, Move.ROCK), Winner.NONE);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.PAPER, Move.PAPER), Winner.NONE);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.SCISSORS, Move.SCISSORS), Winner.NONE);

        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.PAPER, Move.ROCK), Winner.USER);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.SCISSORS, Move.PAPER), Winner.USER);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.ROCK, Move.SCISSORS), Winner.USER);

        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.SCISSORS, Move.ROCK), Winner.SYSTEM);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.ROCK, Move.PAPER), Winner.SYSTEM);
        JudgeJosephDreddService.winnersPredefinedMap.put(new ImmutablePair<>(Move.PAPER, Move.SCISSORS), Winner.SYSTEM);
    }
}