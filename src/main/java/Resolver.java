import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public static Player solve( Characteristics characteristics, int K, Integer M, String selection, String crossOverMethod, String mutationMethod, String evaluatorValue, Long parameterMillis ) {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(characteristics.getPlayerClass()));
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>(), toAdd = new ArrayList<>(), toRemove = new ArrayList<>();
        Evaluator evaluator = evaluator(evaluatorValue, parameterMillis);
        long start = System.currentTimeMillis();
        do {
            for (Player player : playerList) {
                if( playerList.size() > 1) {
                    children.addAll(crossOver(crossOverMethod, player, playerList.get((playerList.indexOf(player) + 1) % playerList.size())));
                    break;
                } else {
                    children.add(player);
                }
            }
            for (Player child : children) {
                if( random.nextBoolean() ) {
                    toRemove.add(child);
                    toAdd.addAll(mutate(mutationMethod, child, characteristics, K));
                }
            }
            children.removeAll(toRemove);
            children.addAll(toAdd);
            playerList = select(selection, children, K, M);
            children.clear();
            toAdd.clear();
            toRemove.clear();
        } while( evaluator.evaluate(start, parameterMillis) );

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

    private static List<Player> crossOver( String crossoverMethod, Player player1, Player player2 ) {
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>();
        switch (crossoverMethod.toUpperCase()) {
            case "SINGLE_POINT":
                children.addAll(Crossovers.singlePoint(player1, player2 , random));
                break;
            case "DOUBLE_POINT":
                children.addAll(Crossovers.doublePoint(player1, player2 , random));
                break;
            case "ANNULAR":
                children.addAll(Crossovers.annular(player1, player2 , random));
                break;
            case "UNIFORM":
                children.addAll(Crossovers.uniform(player1, player2 , random));
                break;
        }
        return children;
    }

    private static List<Player> mutate( String mutationMethod, Player player, Characteristics characteristics, int K) {
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>();
        switch (mutationMethod.toUpperCase()) {
            case "SIMPLE_MUTATION":
                children.addAll(Mutations.simple(random, player, characteristics, K));
                break;
        }
        return  children;
    }

    private static Evaluator evaluator(String evaluatorValue, Long parameterMillis) {
        switch (evaluatorValue.toUpperCase()) {
            case "TIME":
                return new EvaluateTime();
            default:
                return null;
        }

    }
}
