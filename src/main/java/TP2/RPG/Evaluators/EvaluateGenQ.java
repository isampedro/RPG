package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.List;

public class EvaluateGenQ implements Evaluator{
    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen, List<Player>currentGeneration, int maxRoundsNoChange, double structureVariety, double delta, int acceptableSolution) {
        return actualGen < maxGen;
    }
}
