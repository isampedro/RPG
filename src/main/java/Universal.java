import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Universal {
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

        double fitnessTotal = 0;
        for (Player player : players) {
            fitnessTotal += player.getPerformance();
        }

        List<Double> relativeFitness = new ArrayList<>(), cumulatedFitness = new ArrayList<>();
        cumulatedFitness.add(players.get(0).getPerformance()/fitnessTotal);
        relativeFitness.add(players.get(0).getPerformance()/fitnessTotal);
        for( int i = 1; i < players.size(); i++ ) {
            relativeFitness.add(players.get(i).getPerformance()/fitnessTotal);
            cumulatedFitness.add(relativeFitness.get(i) + cumulatedFitness.get(i-1));
            //System.out.println(cumulatedFitness.get(i));
        }

        Random random = new Random(System.currentTimeMillis());
        Double[] rs = new Double[K];
        List<Player> chosen = new ArrayList<>();
        boolean found = false;
        for( int i = 0; i < K; i++ ) {
            rs[i] = (random.nextDouble() + i)/K;
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
