package com.acme.games.rockpaperscissors.main.service;

import com.acme.games.rockpaperscissors.main.domain.Move;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class PredictionService {
    public PredictionService() {
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

    /*public static void main(String[] args) {
        Random r = new Random();

        Map<Move, Float> stats = ImmutableMap.of(Move.ROCK, 0f, Move.PAPER, 1f, Move.SCISSORS, 0f);
        ArrayList<Map<Move, Integer>> randomArray = new ArrayList<>();
        for (int i = 0; i <= 100; i++){
            randomArray.add(stats
                    .entrySet()
                    .stream()
                    .map(e -> new ImmutablePair<>(e.getKey(), getFormula(e, r)))
                    .collect(toMap(ImmutablePair::getLeft, ImmutablePair::getRight)));
        }

        Map<Move, ArrayList<Integer>> result = new HashMap<>();
        for(Map<Move, Integer> map : randomArray) {
            for(Map.Entry<Move, Integer> entry : map.entrySet()) {
                ArrayList<Integer> values = result.get(entry.getKey());
                if(Objects.isNull(values)) {
                    values = new ArrayList<>();
                    result.put(entry.getKey(), values);
                }
                values.add(entry.getValue());
            }
        }

        Map<Move, Integer> percentages = stats.keySet().stream().map(key -> new ImmutablePair<>(key, 0)).collect(toMap(Pair::getKey, Pair::getValue));
        for (Map.Entry<Move, ArrayList<Integer>> moveChances : result.entrySet()) {
            Move key = moveChances.getKey();
            percentages.get(key);
        }

        System.out.println(result);
    }*/

    private static int getFormula(Map.Entry<Move, Float> e, Random r) {
        return r.nextInt((int) (13 * (e.getValue() * 1.7 + 3)));
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