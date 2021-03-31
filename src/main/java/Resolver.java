import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Resolver {
    public static Player solve( Characteristics characteristics, int K, String selection) {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(characteristics.getPlayerClass()));
        Random random = new Random(System.currentTimeMillis());
        int listValue, idValue;
        Item selectedItem;
        List<Player> children = new ArrayList<>();
        do {
            for (Player player : playerList) {
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
                            children.add(player.putWeapon(selectedItem));
                            break;
                        case 1:
                            selectedItem = characteristics.getBoots().get(idValue % characteristics.getBoots().size());
                            children.add(player.putBoots(selectedItem));
                            break;
                        case 2:
                            selectedItem = characteristics.getGloves().get(idValue % characteristics.getGloves().size());
                            children.add(player.putGloves(selectedItem));
                            break;
                        case 3:
                            selectedItem = characteristics.getHelmets().get(idValue % characteristics.getHelmets().size());
                            children.add(player.putHelmet(selectedItem));
                            break;
                        case 4:
                            selectedItem = characteristics.getArmors().get(idValue % characteristics.getArmors().size());
                            children.add(player.putArmor(selectedItem));
                            break;
                        case 5:
                            children.add(player.putHeight(random.nextDouble()*(2 - 1.3) + 1.3));
                            break;
                    }
                }
            }
            switch (selection.toUpperCase()) {
                case "ELITE":
                    playerList = Elite.solve( children, K, characteristics );
                    break;
                case "ROULETTE":
                    playerList = Roulette.solve(characteristics, K);
                    break;
            }
        } while( playerList.get(0).getPerformance() < 19 );
        return playerList.get(0);
    }
}
