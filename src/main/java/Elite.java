import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Elite {
    public static List<Player> solve(Characteristics characteristics, int K) {
        if( K <= 0 ) {
            System.exit(1);
        }

        List<Player> players = new LinkedList<>();

        for (Item weapon : characteristics.getWeapons()) {
            for (Item armor : characteristics.getArmors()) {
                for (Item boot : characteristics.getBoots()) {
                    for (Item helmet : characteristics.getHelmets()) {
                        for (Item glove : characteristics.getGloves()) {
                            players.add(new Player(characteristics.getPlayerClass(), characteristics.getHeight(), weapon, boot, helmet, glove, armor));
                        }
                    }
                }
            }
        }

        players.sort((p1, p2) -> Double.compare(p2.getPerformance(), p1.getPerformance()));

        List<Player> result = new ArrayList<>();
        for( int i = 0; i < K; i++) {
            result.add(players.get(i));
        }

        return result;
    }
}
