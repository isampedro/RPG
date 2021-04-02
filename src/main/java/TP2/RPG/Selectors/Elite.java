package TP2.RPG.Selectors;

import TP2.RPG.Player.Player;

import java.util.*;

public class Elite {
    public List<Player> solve(List<Player> players, int K) {
        if( K <= 0 ) {
            System.exit(1);
        }

        players.sort((p1, p2) -> Double.compare(p2.getPerformance(), p1.getPerformance()));

        List<Player> result = new ArrayList<>();
        for( int i = 0; i < K && i < players.size(); i++) {
            result.add(players.get(i));
        }

        return result;
    }
}
