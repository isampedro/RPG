package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.List;

public class EvaluateAcceptableSolution implements Evaluator {
    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen, List<Player> currentGeneration, int maxRoundsNoChange, double structureVariety, double delta, int acceptableSolution) {
        for (Player player : currentGeneration) {
            if( player.getPerformance() >= acceptableSolution ) {
                return false;
            }
        }
        return true;
    }
}
