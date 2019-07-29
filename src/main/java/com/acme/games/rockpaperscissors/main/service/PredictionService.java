package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.google.common.collect.ImmutableMap;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class PredictionService {
    public PredictionService() {
    }

    Move predictModel(List<Move> trainingSet) {
        if (trainingIsSetTooSmall(trainingSet)) {
            return Move.values()[new Random().nextInt(Move.values().length)];
        } else {
            Map<Move, Float> percentages = analyze(trainingSet);
            return mostExpected(percentages);
        }
    }

    private Move mostExpected(Map<Move, Float> percentages) {
        return percentages
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .findFirst().get().getKey();
    }

    private Map<Move, Float> analyze(List<Move> trainingSet) {
        Map<Move, Float> percentages = ImmutableMap.of(
                Move.PAPER, (float)trainingSet.stream().filter(m -> m.equals(Move.PAPER)).count()/ trainingSet.size(),
                Move.ROCK, (float)trainingSet.stream().filter(m -> m.equals(Move.ROCK)).count()/ trainingSet.size(),
                Move.SCISSORS, (float)trainingSet.stream().filter(m -> m.equals(Move.SCISSORS)).count()/ trainingSet.size()
                );
        return percentages;
    }

    private boolean trainingIsSetTooSmall(List<Move> userMoves) {
        return userMoves.size() < 3;
    }
}