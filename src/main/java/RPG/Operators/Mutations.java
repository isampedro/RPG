package RPG.Operators;

import RPG.Characteristics;
import RPG.Player.Item;
import RPG.Player.Player;

import java.util.Random;

public class Mutations {
    public void simple(Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int gen, idValue;
        double i = random.nextDouble();
        if( i < Pm ) {
            gen = random.nextInt(6);
            idValue = random.nextInt();
            if( idValue < 0 ) {
                idValue *= -1;
            }
            mutatePlayer(player, characteristics, idValue ,gen);
        }
    }

    public void limitedMultigen( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int gensQ = random.nextInt(6) + 1, gen, idValue;
        double j;
        for(int i = 0; i < gensQ; i++) {
            j = random.nextDouble();
            if( j < Pm ) {
                gen = random.nextInt(6);
                idValue = random.nextInt();
                if( idValue < 0 ) {
                    idValue *= -1;
                }
                mutatePlayer(player, characteristics, idValue ,gen);
            }

        }
    }

    public void uniformMultigen( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int idValue;
        double j;
        for(int i = 0; i < 6; i++) {
            j = random.nextDouble();
            if( j < Pm ) {
                idValue = random.nextInt();
                if( idValue < 0 ) {
                    idValue *= -1;
                }
                mutatePlayer(player, characteristics, idValue ,i);
            }
        }
    }

    public void complete( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int idValue;
        double j;
        j = random.nextDouble();
        if( j < Pm ) {
            for(int i = 0; i < 6; i++) {
                idValue = random.nextInt();
                if( idValue < 0 ) {
                    idValue *= -1;
                }
                mutatePlayer(player, characteristics, idValue ,i);
            }
        }
    }

    private void mutatePlayer( Player player, Characteristics characteristics, int idValue, int index ) {
        Item selectedItem;
        Random random = new Random(System.currentTimeMillis());

        switch (index) {
            case 0:
                selectedItem = characteristics.getWeapons().get(idValue % characteristics.getWeapons().size());
                player.setByIndex(1, selectedItem);
                break;
            case 1:
                selectedItem = characteristics.getBoots().get(idValue % characteristics.getBoots().size());
                player.setByIndex(2, selectedItem);
                break;
            case 2:
                selectedItem = characteristics.getGloves().get(idValue % characteristics.getGloves().size());
                player.setByIndex(3, selectedItem);
                break;
            case 3:
                selectedItem = characteristics.getHelmets().get(idValue % characteristics.getHelmets().size());
                player.setByIndex(4, selectedItem);
                break;
            case 4:
                selectedItem = characteristics.getArmors().get(idValue % characteristics.getArmors().size());
                player.setByIndex(5, selectedItem);
                break;
            case 5:
                player.setHeight(random.nextDouble()*(2-1.3) + 1.3);
                break;
        }
    }
}
