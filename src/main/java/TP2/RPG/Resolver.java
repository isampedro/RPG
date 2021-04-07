package TP2.RPG;

import TP2.RPG.Evaluators.*;
import TP2.RPG.Operators.Crossovers;
import TP2.RPG.Operators.Mutations;
import TP2.RPG.Player.Player;
import TP2.RPG.Selectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    Elite elite = new Elite();
    Roulette roulette = new Roulette();
    Ranking ranking = new Ranking();
    Universal universal = new Universal();
    Boltzmann boltzmann = new Boltzmann();
    ProbabilisticTournament probabilisticTournament = new ProbabilisticTournament();
    DeterministicTournament deterministicTournament = new DeterministicTournament();
    Crossovers crossovers = new Crossovers();
    Mutations mutations = new Mutations();

    public List<Player> solve(Characteristics characteristics, int K, Integer M, String selection, String crossOverMethod, String mutationMethod,
                              String evaluatorValue, Long parameterMillis, double Pm, String implementation, String replacement, int N,
                              int maxGen, int startingParents, double A, double B, String secondSelection, String secondReplacement, int maxRoundsNoChange,
                              double structureVariety, double delta, int acceptableSolution, double T0, double Tc ) {
        List<Player> currentGeneration,newGeneration = new ArrayList<>(), population = new ArrayList<>(), auxGeneration, lastGeneration;
        Evaluator evaluator = evaluator(evaluatorValue);
        double minimumFitness, averageFitness, maximumFitness;
        Random random = new Random(System.currentTimeMillis());
        for( int i = 0; i < startingParents; i++ ) {
            population.add(new Player(characteristics.getPlayerClass(), random.nextDouble()*(2-1.3) + 1.3, characteristics.getWeapons().get(random.nextInt(characteristics.getWeapons().size())),
                    characteristics.getBoots().get(random.nextInt(characteristics.getBoots().size())), characteristics.getHelmets().get(random.nextInt(characteristics.getHelmets().size())),
                    characteristics.getGloves().get(random.nextInt(characteristics.getGloves().size())), characteristics.getArmors().get(random.nextInt(characteristics.getArmors().size())),
                    0));
        }
        double similarity;
        boolean isSimilar;
        long start = System.currentTimeMillis(), generation = -1;
        int replaceSize1 = (int) (N*B), replaceSize2 = N - replaceSize1;
        int selectionSize1 = (int) (K*A), selectionSize2 = K - selectionSize1;
        currentGeneration = selectMultiple(selection, secondSelection, selectionSize1, selectionSize2, population, M, T0, Tc);
        do {
            minimumFitness = currentGeneration.get(0).getPerformance();
            maximumFitness = currentGeneration.get(0).getPerformance();
            averageFitness = 0;
            for (Player player : currentGeneration) {
                if( minimumFitness > player.getPerformance() ) {
                    minimumFitness = player.getPerformance();
                }
                if( maximumFitness < player.getPerformance() ) {
                    maximumFitness = player.getPerformance();
                }
                averageFitness += player.getPerformance();
            }
            averageFitness /= currentGeneration.size();

            generation++;
            for( int i = 0; i < currentGeneration.size(); i+=2) {
                newGeneration.addAll(crossOver(crossOverMethod, currentGeneration.get(i), currentGeneration.get((i+1) % currentGeneration.size())));
            }
            for( int i = 0; i < newGeneration.size(); i++ ) {
                newGeneration.set(i, mutate(mutationMethod, newGeneration.get(i), characteristics, Pm));
            }

            population.addAll(newGeneration);
            newGeneration = replace(implementation, replacement, secondReplacement, replaceSize1, replaceSize2, N, newGeneration, currentGeneration, M, T0, Tc);
            auxGeneration = selectMultiple(selection, secondSelection, selectionSize1, selectionSize2, newGeneration, M, T0, Tc);

            similarity = 0;

            for (Player player : auxGeneration) {
                isSimilar = false;
                for( int i = 0; i < currentGeneration.size() && !isSimilar; i++ ) {
                    if( player.isSimilar(currentGeneration.get(i), delta)) {
                        isSimilar = true;
                        currentGeneration.remove(i);
                    }
                }

                if( isSimilar ) {
                    similarity++;
                }
            }

            System.out.println(averageFitness + " " + minimumFitness + " " + (1-(similarity/auxGeneration.size())) + " " + maximumFitness + " ");

            lastGeneration = auxGeneration;
            currentGeneration = new ArrayList<>(auxGeneration);
            auxGeneration.clear();
            newGeneration.clear();
        } while( evaluator != null && evaluator.evaluate(start, parameterMillis, generation, maxGen, currentGeneration, maxRoundsNoChange, structureVariety, delta, acceptableSolution) );

        minimumFitness = currentGeneration.get(0).getPerformance();
        maximumFitness = currentGeneration.get(0).getPerformance();

        for (Player player : currentGeneration) {
            if( minimumFitness > player.getPerformance() ) {
                minimumFitness = player.getPerformance();
            }
            if( maximumFitness < player.getPerformance() ) {
                maximumFitness = player.getPerformance();
            }
            averageFitness += player.getPerformance();
        }
        averageFitness /= currentGeneration.size();

        similarity = 0;

        for (Player player : lastGeneration) {
            isSimilar = false;
            for( int i = 0; i < currentGeneration.size() && !isSimilar; i++ ) {
                if( player.isSimilar(currentGeneration.get(i), delta)) {
                    isSimilar = true;
                    currentGeneration.remove(i);
                }
            }

            if( isSimilar ) {
                similarity++;
            }
        }

        System.out.println(averageFitness + " " + minimumFitness + " " + (1-(similarity/lastGeneration.size())) + " " + maximumFitness + " ");
        return currentGeneration;
    }

    private List<Player> replace( String implementation, String implementationMethod, String secondImplementationMethod, int replaceA, int replaceB, int N,
                                  List<Player> newGeneration, List<Player> currentGeneration, int M, double T0, double Tc ) {
        List<Player> playersTotal = new ArrayList<>(), aux = new ArrayList<>();
        switch (implementation.toUpperCase()) {
            case "FILL_PARENT":
                if( newGeneration.size() > N ) {
                    playersTotal.addAll(select(implementationMethod, newGeneration, replaceA, M, T0, Tc));
                    playersTotal.addAll(select(secondImplementationMethod, newGeneration, replaceB, M, T0, Tc));
                } else {
                    playersTotal.addAll(newGeneration);
                    playersTotal.addAll(select(implementationMethod, currentGeneration, replaceA, M, T0, Tc));
                    playersTotal.addAll(select(secondImplementationMethod, currentGeneration, replaceB, M, T0, Tc));
                }
                break;
            case "FILL_ALL":
                aux.addAll(currentGeneration);
                aux.addAll(newGeneration);
                playersTotal.addAll(select(implementationMethod, aux, replaceA, M, T0, Tc));
                playersTotal.addAll(select(secondImplementationMethod, aux, replaceB, M, T0, Tc));
                aux.clear();
                break;
            default:
                System.out.println("The evaluator doesn't have a correct name.");
                System.exit(1);
        }
        return playersTotal;
    }

    private List<Player> selectMultiple(String selectionMethod, String secondSelectionMethod, int selectA, int selectB , List<Player> children, Integer M, double T0, double Tc) {
        List<Player> result = new ArrayList<>();
        result.addAll(select(selectionMethod, children, selectA, M, T0, Tc));
        result.addAll(select(secondSelectionMethod, children, selectB, M, T0, Tc));
        return result;
    }

    private List<Player> select(String selectionMethod, List<Player> children, int K, Integer M, double T0, double Tc) {
        List<Player> playerList = new ArrayList<>();
        Player maxPerformancePlayer = children.get(0);
        for (Player child : children) {
            if( maxPerformancePlayer.getPerformance() < child.getPerformance()  ) {
                maxPerformancePlayer = child;
            }
        }

        switch (selectionMethod.toUpperCase()) {
            case "ELITE":
                playerList = elite.solve( children, K-1 );
                break;
            case "ROULETTE":
                playerList = roulette.solve(children, K-1);
                break;
            case "RANKING":
                playerList = ranking.solve(children, K-1);
                break;
            case "UNIVERSAL":
                playerList = universal.solve(children, K-1);
                break;
            case "PROBABILISTIC_TOURNAMENT":
                playerList = probabilisticTournament.solve(children, K-1);
                break;
            case "DETERMINISTIC_TOURNAMENT":
                playerList = deterministicTournament.solve(children, K-1, M);
                break;
            case "BOLTZMANN":
                playerList = boltzmann.solve(children, K-1, T0, Tc );
                break;
            default:
                System.out.println("At least one of the selectors/replacers doesn't have a correct name.");
                System.exit(1);
        }
        playerList.add(maxPerformancePlayer);
        return playerList;
    }

    private List<Player> crossOver( String crossoverMethod, Player player1, Player player2 ) {
        Random random = new Random(System.currentTimeMillis());
        List<Player> children = new ArrayList<>();

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
            default:
                System.out.println("The crossover method doesn't have a correct name.");
                System.exit(1);
        }
        return children;
    }

    private Player mutate( String mutationMethod, Player player, Characteristics characteristics, double Pm) {
        switch (mutationMethod.toUpperCase()) {
            case "SIMPLE_MUTATION":
                player = mutations.simple(player, characteristics, Pm);
                break;
            case "LIMITED_MULTIGEN":
                player = mutations.limitedMultigen(player, characteristics, Pm);
                break;
            case "UNIFORM_MULTIGEN":
                player = mutations.uniformMultigen(player, characteristics, Pm);
                break;
            case "COMPLETE_MUTATION":
                player = mutations.complete(player, characteristics, Pm);
                break;
            default:
                System.out.println("The mutation method doesn't have a correct name.");
                System.exit(1);
        }
        return player;
    }

    private Evaluator evaluator(String evaluatorValue) {
        switch (evaluatorValue.toUpperCase()) {
            case "TIME":
                return new EvaluateTime();
            case "GEN_Q":
                return new EvaluateGenQ();
            case "CONTENT":
                return new EvaluateMax();
            case "STRUCTURE":
                return new EvaluateStructure();
            case "ACCEPTABLE_SOLUTION":
                return new EvaluateAcceptableSolution();
            default:
                System.out.println("The evaluator doesn't have a correct name.");
                System.exit(1);
                return null;
        }
    }
}
