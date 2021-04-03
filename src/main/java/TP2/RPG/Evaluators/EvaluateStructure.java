package TP2.RPG.Evaluators;

import TP2.RPG.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class EvaluateStructure implements Evaluator{
    private List<Player> previousGeneration;
    private int generationsCounter = 0;
    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen, List<Player> currentGeneration, int maxRoundsNoChange, double structureVariety, double delta, int acceptableSolution) {
        if( previousGeneration == null ) {
            previousGeneration = new ArrayList<>(currentGeneration);
            return true;
        }
        double similarity = 0;
        boolean isSimilar;
        List<Player> auxPreviousGeneration = new ArrayList<>(previousGeneration);

        for (Player player : currentGeneration) {
            isSimilar = false;
            for( int i = 0; i < auxPreviousGeneration.size() && !isSimilar; i++ ) {
                if( player.isSimilar(auxPreviousGeneration.get(i), delta)) {
                    isSimilar = true;
                    auxPreviousGeneration.remove(i);
                }
            }

            if( isSimilar ) {
                similarity++;
            }
        }
        previousGeneration = new ArrayList<>(currentGeneration);
        if( similarity/currentGeneration.size() <= (1-structureVariety)) {
            generationsCounter++;
        } else {
            generationsCounter = 0;
        }

        return  generationsCounter < maxRoundsNoChange;
    }
}
