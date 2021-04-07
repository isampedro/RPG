package TP2.RPG.Selectors;

import TP2.RPG.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boltzmann {

    public List<Player> solve(List<Player> players, int K, double T0, double Tc) {
        if( K <= 0 ) {
            System.exit(1);
        }

        double[] expVal = new double[players.size()];
        for( int i = 0; i < players.size(); i++ ) {
            expVal[i] = expValCalculation(calculateT(T0, Tc, players.get(i).getGeneration()), players.get(i), players);
        }

        double fitnessTotal = 0;
        for(int i = 0; i < players.size(); i++ ) {
            fitnessTotal += expVal[i];
        }

        List<Double> relativeFitness = new ArrayList<>(), cumulatedFitness = new ArrayList<>();
        cumulatedFitness.add(expVal[0]/fitnessTotal);
        relativeFitness.add(expVal[0]/fitnessTotal);
        for( int i = 1; i < players.size(); i++ ) {
            relativeFitness.add(expVal[i]/fitnessTotal);
            cumulatedFitness.add(relativeFitness.get(i) + cumulatedFitness.get(i-1));
        }

        Random random = new Random(System.currentTimeMillis());
        Double[] rs = new Double[K];
        List<Player> chosen = new ArrayList<>();
        boolean found = false;
        for( int i = 0; i < K && i < players.size(); i++ ) {
            rs[i] = random.nextDouble();
            if(rs[i] < cumulatedFitness.get(0)) {
                chosen.add(players.get(0));
            } else {
                for( int j = 1; j < players.size() && !found; j++ ) {
                    if( cumulatedFitness.get(j-1) < rs[i] && rs[i] <= cumulatedFitness.get(j) ) {
                        chosen.add(players.get(j));
                        found = true;
                    }
                }
                found = false;
            }
        }
        return chosen;
    }

    private double calculateT( double T0, double Tc, int generation) {
        return Tc + (T0 - Tc)*Math.exp(-generation);
    }

    private double expValCalculation(double T, Player player, List<Player> players ) {
        double totalPerformance = getTotalPerformance(players);
        double A = Math.exp(player.getPerformance()/T);
        double B = Math.exp(totalPerformance/T)/players.size();
        return A/B;
    }

    private double getTotalPerformance(List<Player> players) {
        double totalPerformance = 0.0;
        for (Player player : players) {
            totalPerformance += player.getPerformance();
        }
        return totalPerformance;
    }
}
