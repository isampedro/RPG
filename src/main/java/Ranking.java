import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ranking {
    public List<Player> solve(List<Player> players, int K) {
        if( K <= 0 ) {
            System.exit(1);
        }

        players.sort((p1, p2) -> Double.compare(p2.getPerformance(), p1.getPerformance()));

        double N = players.size();
        double aux, totalFitness = 0;
        List<Double> pseudoPerformance = new ArrayList<>();
        for( int i = 0; i < players.size(); i++ ) {
            aux = (N-i+1)/ N;
            pseudoPerformance.add(aux);
            totalFitness += aux;
        }

        List<Double> relativeFitness = new ArrayList<>(), cumulatedFitness = new ArrayList<>();
        cumulatedFitness.add(pseudoPerformance.get(0)/totalFitness);
        relativeFitness.add(pseudoPerformance.get(0)/totalFitness);
        for( int i = 1; i < players.size(); i++ ) {
            relativeFitness.add(pseudoPerformance.get(i) /totalFitness);
            cumulatedFitness.add(relativeFitness.get(i) + cumulatedFitness.get(i-1));
            //System.out.println(cumulatedFitness.get(i));
        }

        Random random = new Random(System.currentTimeMillis());
        Double[] rs = new Double[K];
        List<Player> chosen = new ArrayList<>();
        boolean found = false;
        for( int i = 0; i < K; i++ ) {
            rs[i] = random.nextDouble();
            System.out.println("Random " + i + " is " + rs[i]);
            if(rs[i] < cumulatedFitness.get(0)) {
                chosen.add(players.get(0));
                System.out.println("Cumulated Fitness: " + cumulatedFitness.get(0));
            } else {
                for( int j = 1; j < players.size() && !found; j++ ) {
                    if( cumulatedFitness.get(j-1) < rs[i] && rs[i] <= cumulatedFitness.get(j) ) {
                        chosen.add(players.get(j));
                        found = true;
                        System.out.println("Cumulated Fitness: " + cumulatedFitness.get(j));
                    }
                }
                found = false;
            }
        }

        return chosen;
    }
}
