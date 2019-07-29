package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.Collectors.toMap;

public class PredictionService {
    public PredictionService() {
    }

    private static int getFormula(Map.Entry<Move, Float> e, Random r) {
        return r.nextInt((int) (13 * (e.getValue() * 3.7 + 3)));
    }

    Move predictModel(List<Move> trainingSet) {
        if (trainingIsSetTooSmall(trainingSet)) {
            return Move.values()[new Random().nextInt(Move.values().length)];
        } else {
            Map<Move, Float> stats = analyze(trainingSet);
            return mostExpected(stats);
        }
    }

    private Move mostExpected(Map<Move, Float> percentages) {
        Random r = new Random();
        Map<Move, Integer> chancesMap = percentages
                .entrySet()
                .stream()
                .map(e -> new ImmutablePair<>(e.getKey(), getFormula(e, r)))
                .collect(toMap(ImmutablePair::getLeft, ImmutablePair::getRight));
        return chancesMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .findFirst()
                .get()
                .getKey();
    }

    private Map<Move, Float> analyze(List<Move> trainingSet) {
        Map<Move, Float> stats = ImmutableMap.of(
                Move.PAPER, (float) trainingSet.stream().filter(m -> m.equals(Move.PAPER)).count() / trainingSet.size(),
                Move.ROCK, (float) trainingSet.stream().filter(m -> m.equals(Move.ROCK)).count() / trainingSet.size(),
                Move.SCISSORS, (float) trainingSet.stream().filter(m -> m.equals(Move.SCISSORS)).count() / trainingSet.size()
        );
        return stats;
    }

    private boolean trainingIsSetTooSmall(List<Move> userMoves) {
        return userMoves.size() < 3;
    }
}