import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public Player solve( Characteristics characteristics, int K, Integer M, String selection, String crossOverMethod, String mutationMethod,
                                String evaluatorValue, Long parameterMillis, double Pm, String implementation, int N ) {
        List<Player> currentGeneration = new ArrayList<>();
        currentGeneration.add(new Player(characteristics.getPlayerClass(), 0));
        List<Player> newGeneration = new ArrayList<>();
        Evaluator evaluator = evaluator(evaluatorValue);
        long start = System.currentTimeMillis();
        do {
            for (Player player : currentGeneration) {
                if( currentGeneration.size() > 1) {
                    newGeneration.addAll(crossOver(crossOverMethod, player, currentGeneration.get((currentGeneration.indexOf(player) + 1) % currentGeneration.size())));
                    break;
                } else {
                    newGeneration.add(player);
                }
                newGeneration.addAll(currentGeneration);
            }
            for (Player child : newGeneration) {
                mutate(mutationMethod, child, characteristics, Pm);
            }
            filterByImplementation(implementation, newGeneration, currentGeneration, N);
            currentGeneration = select(selection, newGeneration, K, M);
            newGeneration.clear();
        } while( evaluator != null && evaluator.evaluate(start, parameterMillis) );

        return currentGeneration.get(0);
    }

    private void filterByImplementation( String implementation, List<Player> newGeneration, List<Player> currentGeneration, int N ) {
        switch (implementation.toUpperCase()) {
            case "FILL_PARENT":
                
            case "FILL_ALL":
        }
    }

    private List<Player> select(String selectionMethod, List<Player> children, int K, Integer M) {
        List<Player> playerList = new ArrayList<>();
        Elite elite = new Elite();
        Roulette roulette = new Roulette();
        Ranking ranking = new Ranking();
        Universal universal = new Universal();
        ProbabilisticTournament probabilisticTournament = new ProbabilisticTournament();
        DeterministicTournament deterministicTournament = new DeterministicTournament();
        switch (selectionMethod.toUpperCase()) {
            case "ELITE":
                playerList = elite.solve( children, K );
                break;
            case "ROULETTE":
                playerList = roulette.solve(children, K);
                break;
            case "RANKING":
                playerList = ranking.solve(children, K);
                break;
            case "UNIVERSAL":
                playerList = universal.solve(children, K);
                break;
            case "PROBABILISTIC_TOURNAMENT":
                playerList = probabilisticTournament.solve(children, K);
                break;
            case "DETERMINISTIC_TOURNAMENT":
                playerList = deterministicTournament.solve(children, K, M);
                break;
        }
        return playerList;
    }

    private List<Player> crossOver( String crossoverMethod, Player player1, Player player2 ) {
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>();
        Crossovers crossovers = new Crossovers();
        switch (crossoverMethod.toUpperCase()) {
            case "SINGLE_POINT":
                children.addAll(crossovers.singlePoint(player1, player2 , random));
                break;
            case "DOUBLE_POINT":
                children.addAll(crossovers.doublePoint(player1, player2 , random));
                break;
            case "ANNULAR":
                children.addAll(crossovers.annular(player1, player2 , random));
                break;
            case "UNIFORM":
                children.addAll(crossovers.uniform(player1, player2 , random));
                break;
        }
        return children;
    }

    private void mutate( String mutationMethod, Player player, Characteristics characteristics, double Pm) {
        Mutations mutations = new Mutations();
        switch (mutationMethod.toUpperCase()) {
            case "SIMPLE_MUTATION":
                mutations.simple(player, characteristics, Pm);
                break;
            case "LIMITED_MULTIGEN":
                mutations.limitedMultigen(player, characteristics, Pm);
                break;
            case "UNIFORM_MULTIGEN":
                mutations.uniformMultigen(player, characteristics, Pm);
                break;
            case "COMPLETE_MUTATION":
                mutations.complete(player, characteristics, Pm);
                break;
        }
    }

    private Evaluator evaluator(String evaluatorValue) {
        switch (evaluatorValue.toUpperCase()) {
            case "TIME":
                return new EvaluateTime();
            default:
                return null;
        }

    }
}
