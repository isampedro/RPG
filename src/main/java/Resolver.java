import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public List<Player> solve( Characteristics characteristics, int K, Integer M, String selection, String crossOverMethod, String mutationMethod,
                                String evaluatorValue, Long parameterMillis, double Pm, String implementation, String replacement, int N,
                               int maxGen, int startingParents, double A, double B, String secondSelection, String secondReplacement ) {
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
        long start = System.currentTimeMillis(), generation = -1;
        int replaceSize1 = (int) (N*B), replaceSize2 = N - replaceSize1;
        int selectionSize1 = (int) (K*A), selectionSize2 = K - selectionSize1;
        currentGeneration = selectMultiple(selection, secondSelection, selectionSize1, selectionSize2, population, M);
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
            newGeneration = replace(implementation, replacement, secondReplacement, replaceSize1, replaceSize2, N, newGeneration, currentGeneration, M);
            currentGeneration = selectMultiple(selection, secondSelection, selectionSize1, selectionSize2, newGeneration, M);
            newGeneration.clear();
        } while( evaluator != null && evaluator.evaluate(start, parameterMillis, generation, maxGen) );

        return currentGeneration;
    }

    private List<Player> replace( String implementation, String implementationMethod, String secondImplementationMethod, int replaceA, int replaceB, int N,
                                  List<Player> newGeneration, List<Player> currentGeneration, int M ) {
        List<Player> playersTotal = new ArrayList<>(), aux = new ArrayList<>();
        switch (implementation.toUpperCase()) {
            case "FILL_PARENT":
                if( newGeneration.size() > N ) {
                    playersTotal.addAll(select(implementationMethod, newGeneration, replaceA, M));
                    playersTotal.addAll(select(secondImplementationMethod, newGeneration, replaceB, M));
                } else {
                    playersTotal.addAll(newGeneration);
                    playersTotal.addAll(select(implementationMethod, currentGeneration, replaceA, M));
                    playersTotal.addAll(select(secondImplementationMethod, currentGeneration, replaceB, M));
                }
                break;
            case "FILL_ALL":
                aux.addAll(currentGeneration);
                aux.addAll(newGeneration);
                playersTotal.addAll(select(implementationMethod, aux, replaceA, M));
                playersTotal.addAll(select(secondImplementationMethod, aux, replaceB, M));
                aux.clear();
                break;
        }
        return playersTotal;
    }

    private List<Player> selectMultiple(String selectionMethod, String secondSelectionMethod, int selectA, int selectB , List<Player> children, Integer M) {
        List<Player> result = new ArrayList<>();
        result.addAll(select(selectionMethod, children, selectA, M));
        result.addAll(select(secondSelectionMethod, children, selectB, M));
        return result;
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
