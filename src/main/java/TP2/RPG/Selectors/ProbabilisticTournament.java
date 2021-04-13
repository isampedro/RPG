package TP2.RPG.Selectors;

import TP2.RPG.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProbabilisticTournament {
    public List<Player> solve(List<Player> players, int K, Random random, double threshold) {
        if( K <= 0 ) {
            System.exit(1);
        }

        List<Player> chosen = new ArrayList<>();
        int index1, index2;
        double r;
        for( int i = 0; i < K; i++ ) {
            r = random.nextDouble();
            index1 = random.nextInt(players.size());
            index2 = random.nextInt(players.size());
            if( r < threshold ) {
                if( players.get(index1).getPerformance() < players.get(index2).getPerformance()) {
                    chosen.add(players.get(index2));
                } else {
                    chosen.add(players.get(index1));
                }
            } else {
                if( players.get(index1).getPerformance() > players.get(index2).getPerformance()) {
                    chosen.add(players.get(index2));
                } else {
                    chosen.add(players.get(index1));
                }
            }
        }

        return chosen;
    }
}
