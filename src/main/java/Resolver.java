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
                            children.addAll(simpleMutate(random, player, characteristics, K));
                            break;
                        case 1:
                            children.addAll(singlePointCrossover(player, playerList.get((playerList.indexOf(player) + 1) % playerList.size()) , random));
                            break;
                    }
                } else {
                    children.addAll(simpleMutate(random, player, characteristics, K));
                }
            }
            playerList = select(selection, children, K, M);
            children.clear();
        } while( System.currentTimeMillis() - start < 10000 );

        for (Player player : playerList) {
            //System.out.println(player);
        }
        return playerList.get(0);
    }

    private static List<Player> singlePointCrossover(Player p1, Player p2, Random random) {
        int listValue;
        List<Player> children = new ArrayList<>();

        listValue = random.nextInt(6);

        switch (listValue+1) {
            case 1:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1));
                children.add(new Player(p1.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1));
                break;
            case 2:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1));
                break;
            case 3:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p1.getGloves(), p1.getArmor(), p1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p2.getGloves(), p2.getArmor(), p1));
                break;
            case 4:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p1.getArmor(), p1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p2.getArmor(), p1));
                break;
            case 5:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1));
                break;
            case 6:
                children.add(new Player(p1.getPlayerClass(), p2.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1));
                children.add(new Player(p2.getPlayerClass(), p1.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1));
                break;
        }

        return children;
    }

    private static List<Player> simpleMutate(Random random, Player player, Characteristics characteristics, int K) {
        int listValue, idValue;
        Item selectedItem;
        List<Player> children = new ArrayList<>();

        for( int i = 0; i < K; i++ ) {
            listValue = random.nextInt();
            if( listValue < 0 ) {
                listValue *= -1;
            }
            idValue = random.nextInt();
            if( idValue < 0 ) {
                idValue *= -1;
            }
            switch (listValue % 6) {
                case 0:
                    selectedItem = characteristics.getWeapons().get(idValue % characteristics.getWeapons().size());
                    children.add(new Player(player.getPlayerClass(), player.getHeight(), selectedItem, player.getBoots(), player.getHelmet(), player.getGloves(), player.getArmor(), player));
                    break;
                case 1:
                    selectedItem = characteristics.getBoots().get(idValue % characteristics.getBoots().size());
                    children.add(new Player(player.getPlayerClass(), player.getHeight(), player.getWeapon(), selectedItem, player.getHelmet(), player.getGloves(), player.getArmor(), player));
                    break;
                case 2:
                    selectedItem = characteristics.getGloves().get(idValue % characteristics.getGloves().size());
                    children.add(new Player(player.getPlayerClass(), player.getHeight(), player.getWeapon(), player.getBoots(), player.getHelmet(), selectedItem, player.getArmor(), player));
                    break;
                case 3:
                    selectedItem = characteristics.getHelmets().get(idValue % characteristics.getHelmets().size());
                    children.add(new Player(player.getPlayerClass(), player.getHeight(), player.getWeapon(), player.getBoots(), selectedItem, player.getGloves(), player.getArmor(), player));
                    break;
                case 4:
                    selectedItem = characteristics.getArmors().get(idValue % characteristics.getArmors().size());
                    children.add(new Player(player.getPlayerClass(), player.getHeight(), player.getWeapon(), player.getBoots(), player.getHelmet(), player.getGloves(), selectedItem, player));
                    break;
                case 5:
                    children.add(new Player(player.getPlayerClass(), random.nextDouble()*(2 - 1.3) + 1.3, player.getWeapon(), player.getBoots(), player.getHelmet(), player.getGloves(), player.getArmor(), player));
                    break;
            }
        }
        return children;
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
