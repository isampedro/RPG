import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public List<Player> solve( Characteristics characteristics, int K, Integer M, String selection, String crossOverMethod, String mutationMethod,
                                String evaluatorValue, Long parameterMillis, double Pm, String implementation, String implementationMethod, int N, int maxGen, int startingParents ) {
        List<Player> currentGeneration,newGeneration = new ArrayList<>(), population = new ArrayList<>();
        Evaluator evaluator = evaluator(evaluatorValue);
        double minimumFitness, averageFitness;
        Random random = new Random(System.currentTimeMillis());
        for( int i = 0; i < startingParents; i++ ) {
            population.add(new Player(characteristics.getPlayerClass(), random.nextDouble()*(2-1.3) + 1.3, characteristics.getWeapons().get(random.nextInt(characteristics.getWeapons().size())),
                    characteristics.getBoots().get(random.nextInt(characteristics.getBoots().size())), characteristics.getHelmets().get(random.nextInt(characteristics.getHelmets().size())),
                    characteristics.getGloves().get(random.nextInt(characteristics.getGloves().size())), characteristics.getArmors().get(random.nextInt(characteristics.getArmors().size())),
                    0));
        }
        currentGeneration = new ArrayList<>(population);
        long start = System.currentTimeMillis(), generation = -1;
        do {
            minimumFitness = currentGeneration.get(0).getPerformance();
            averageFitness = 0;
            for (Player player : currentGeneration) {
                if( minimumFitness > player.getPerformance() ) {
                    minimumFitness = player.getPerformance();
                }
                averageFitness += player.getPerformance();
            }
            averageFitness /= currentGeneration.size();
            System.out.println(averageFitness + " " + minimumFitness + " ");

            generation++;
            for( int i = 0; i < currentGeneration.size(); i+=2) {
                newGeneration.addAll(crossOver(crossOverMethod, currentGeneration.get(i), currentGeneration.get(i+1)));
            }
            for (Player child : newGeneration) {
                mutate(mutationMethod, child, characteristics, Pm);
            }
            population.addAll(newGeneration);
            newGeneration = filterByImplementation(implementation, implementationMethod, newGeneration, currentGeneration, N, M);
            currentGeneration = select(selection, newGeneration, K, M);
            newGeneration.clear();
        } while( evaluator != null && evaluator.evaluate(start, parameterMillis, generation, maxGen) );

        return currentGeneration;
    }

    private List<Player> filterByImplementation( String implementation, String implementationMethod, List<Player> newGeneration, List<Player> currentGeneration, int N, int M ) {
        List<Player> playersTotal = new ArrayList<>(), aux = new ArrayList<>();
        switch (implementation.toUpperCase()) {
            case "FILL_PARENT":
                if( newGeneration.size() > N ) {
                    playersTotal.addAll(select(implementationMethod, newGeneration, N, M));
                } else {
                    playersTotal.addAll(newGeneration);
                    playersTotal.addAll(select(implementationMethod, currentGeneration, N - newGeneration.size(), M));
                }
                break;
            case "FILL_ALL":
                aux.addAll(currentGeneration);
                aux.addAll(newGeneration);
                playersTotal.addAll(select(implementationMethod, aux, N, M));
                aux.clear();
                break;
        }
        return playersTotal;
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
            case "GEN_Q":
                return new EvaluateGenQ();
            default:
                return null;
        }

    }
}
