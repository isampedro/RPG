package TP2.RPG.Selectors;

import TP2.RPG.Player.Player;

import java.util.*;

public class DeterministicTournament {
    public List<Player> solve(List<Player> players, int K, int M, Random random) {
        if( K <= 0 ) {
            System.exit(1);
        }

        List<Player> chosen = new ArrayList<>();
        Player winner = null;
        int index;
        for( int i = 0; i < K && i < players.size(); i++ ) {
            for( int j = 0; j < M; j++ ) {
                index = random.nextInt(players.size());
                if( winner == null || winner.getPerformance() < players.get(index).getPerformance() ) {
                    winner = players.get(index);
                }
            }
            chosen.add(winner);
            winner = null;
        }

        return chosen;
    }
}
