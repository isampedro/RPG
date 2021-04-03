package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.List;

public class EvaluateTime implements Evaluator{
    @Override
    public boolean evaluate(long startTime, long maxMillis, long evaluateTime, long maxGen, List<Player> currentGeneration, int maxRoundsNoChange, double structureVariety, double delta, int acceptableSolution) {
        return System.currentTimeMillis() - startTime < maxMillis;
    }
}
