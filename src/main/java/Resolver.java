import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public static Player solve( Characteristics characteristics, int K, Integer M, String selection) {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(characteristics.getPlayerClass()));
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>();
        long start = System.currentTimeMillis();
        do {
            for (Player player : playerList) {
                if( playerList.size() > 1) {
                    switch (random.nextInt(2)) {
                        case 0:
                            children.addAll(Mutations.simple(random, player, characteristics, K));
                            break;
                        case 1:
                            switch (random.nextInt(3)) {
                                case 0:
                                    children.addAll(Crossovers.singlePoint(player, playerList.get((playerList.indexOf(player) + 1) % playerList.size()) , random));
                                    break;
                                case 1:
                                    children.addAll(Crossovers.doublePoint(player, playerList.get((playerList.indexOf(player) + 1) % playerList.size()) , random));
                                    break;
                                case 2:
                                    children.addAll(Crossovers.annular(player, playerList.get((playerList.indexOf(player) + 1) % playerList.size()) , random));
                                    break;
                            }
                    }
                } else {
                    children.addAll(Mutations.simple(random, player, characteristics, K));
                }
            }
            playerList = select(selection, children, K, M);
            children.clear();
        } while( System.currentTimeMillis() - start < 10000 );

        return playerList.get(0);
    }

    private static List<Player> select(String selectionMethod, List<Player> children, int K, Integer M) {
        List<Player> playerList = new ArrayList<>();
        switch (selectionMethod.toUpperCase()) {
            case "ELITE":
                playerList = Elite.solve( children, K );
                break;
            case "ROULETTE":
                playerList = Roulette.solve(children, K);
                break;
            case "RANKING":
                playerList = Ranking.solve(children, K);
                break;
            case "UNIVERSAL":
                playerList = Universal.solve(children, K);
                break;
            case "PROBABILISTIC_TOURNAMENT":
                playerList = ProbabilisticTournament.solve(children, K);
                break;
            case "DETERMINISTIC_TOURNAMENT":
                playerList = DeterministicTournament.solve(children, K, M);
                break;
        }
        return playerList;
    }
}
