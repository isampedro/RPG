package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class EvaluateStructure implements Evaluator{
    private List<Player> previousGeneration;
    private int generationsCounter = 0;
    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen, List<Player> currentGeneration,
                            int maxRoundsNoChange, double minimumAcceptableStructureDiversity, double delta, int acceptableSolution) {
        if( previousGeneration == null ) {
            previousGeneration = new ArrayList<>(currentGeneration);
            return true;
        }

        boolean isSimilar;
        int similarity = 0;

        for (Player player : currentGeneration) {
            isSimilar = false;
            for( int i = 0; i < previousGeneration.size() && !isSimilar; i++ ) {
                if( player.isSimilar(previousGeneration.get(i), delta)) {
                    isSimilar = true;
                }
            }

            if( isSimilar ) {
                similarity++;
            }
        }

        if( similarity/(double)currentGeneration.size() > (1-minimumAcceptableStructureDiversity) ) {
            generationsCounter++;
        } else {
            generationsCounter = 0;
        }

        previousGeneration = new ArrayList<>(currentGeneration);

        return generationsCounter < maxRoundsNoChange;
    }
}
