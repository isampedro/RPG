import java.util.*;

public class Elite {
    public static List<Player> solve(List<Player> players, int K, Characteristics characteristics) {
        if( K <= 0 ) {
            System.exit(1);
        }

        players.sort((p1, p2) -> Double.compare(p2.getPerformance(), p1.getPerformance()));

        List<Player> result = new ArrayList<>();
        for( int i = 0; i < K; i++) {
            result.add(players.get(i));
        }

        return result;
    }
}
