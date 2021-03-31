import java.util.*;

public class DeterministicTournament {
    public static List<Player> solve( List<Player> players, int K, int M) {
        if( K <= 0 ) {
            System.exit(1);
        }

        List<Player> chosen = new ArrayList<>();
        Player winner = null;
        int index;
        Random random = new Random(System.currentTimeMillis());
        for( int i = 0; i < K; i++ ) {
            for( int j = 0; j < M; j++ ) {
                index = random.nextInt(players.size()-1);
                if( winner == null || winner.getPerformance() < players.get(index).getPerformance() ) {
                    winner = players.get(index);
                }
            }
            chosen.add(winner);
        }

        return chosen;
    }
}
