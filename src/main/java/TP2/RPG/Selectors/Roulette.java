package TP2.RPG.Selectors;

import TP2.RPG.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette {
    public List<Player> solve(List<Player> players, int K, Random random) {
        if( K <= 0 ) {
            System.exit(1);
        }

        double fitnessTotal = 0;
        for (Player player : players) {
            fitnessTotal += player.getPerformance();
        }

        List<Double> relativeFitness = new ArrayList<>(), cumulatedFitness = new ArrayList<>();
        cumulatedFitness.add(players.get(0).getPerformance()/fitnessTotal);
        relativeFitness.add(players.get(0).getPerformance()/fitnessTotal);
        for( int i = 1; i < players.size(); i++ ) {
            relativeFitness.add(players.get(i).getPerformance()/fitnessTotal);
            cumulatedFitness.add(relativeFitness.get(i) + cumulatedFitness.get(i-1));
        }

        Double[] rs = new Double[K];
        List<Player> chosen = new ArrayList<>();
        boolean found = false;
        for( int i = 0; i < K && i < players.size(); i++ ) {
            rs[i] = random.nextDouble();
//            System.out.println("Random " + i + " is " + rs[i]);
            if(rs[i] < cumulatedFitness.get(0)) {
                chosen.add(players.get(0));
//                System.out.println("Cumulated Fitness: " + cumulatedFitness.get(0));
            } else {
                for( int j = 1; j < players.size() && !found; j++ ) {
                    if( cumulatedFitness.get(j-1) < rs[i] && rs[i] <= cumulatedFitness.get(j) ) {
                        chosen.add(players.get(j));
                        found = true;
//                        System.out.println("Cumulated Fitness: " + cumulatedFitness.get(j));
                    }
                }
                found = false;
            }
        }

        return chosen;
    }
}
