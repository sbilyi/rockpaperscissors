package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.acme.games.rockpaperscissors.main.domain.Winner;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

/**
 * System\User  Rock     Paper       Scissors
 * Rock          =      USER         SYSTEM
 * Paper        SYSTEM   =           USER
 * Scissors     USER    SYSTEM        =
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