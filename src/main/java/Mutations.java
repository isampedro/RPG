import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mutations {
    public static List<Player> simple(Random random, Player player, Characteristics characteristics, int K) {
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
}
