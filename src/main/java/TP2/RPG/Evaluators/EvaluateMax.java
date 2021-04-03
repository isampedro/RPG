package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.List;

public class EvaluateMax implements Evaluator {
    private int counter = 0;
    private Player max;

    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen, List<Player> currentGeneration, int maxRoundsNoChange, double structureVariety, double delta, int acceptableSolution) {
        Player aux = currentGeneration.get(0);
        for (Player player : currentGeneration) {
            if( aux.getPerformance() < player.getPerformance() ) {
                aux = player;
            }
        }
        if( max == null || max.getPerformance() < aux.getPerformance() ) {
            counter = 0;
            max = aux;
        }
        else {
            counter++;
        }

        return counter < maxRoundsNoChange;
    }
}
