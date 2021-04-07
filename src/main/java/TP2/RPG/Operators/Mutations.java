package TP2.RPG.Operators;

import TP2.RPG.Characteristics;
import TP2.RPG.Player.Item;
import TP2.RPG.Player.Player;

import java.util.Random;

public class Mutations {
    public void simple(Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int gen;
        double i = random.nextDouble();
        if( i < Pm ) {
            gen = random.nextInt(6);
            mutatePlayer(player, characteristics ,gen);
        }
    }

    public void limitedMultigen( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        int gensQ = random.nextInt(6) + 1, gen;
        double j;
        for(int i = 0; i < gensQ; i++) {
            j = random.nextDouble();
            if( j < Pm ) {
                gen = random.nextInt(6);
                mutatePlayer(player, characteristics ,gen);
            }

        }
    }

    public void uniformMultigen( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        double j;
        for(int i = 0; i < 6; i++) {
            j = random.nextDouble();
            if( j < Pm ) {
                mutatePlayer(player, characteristics ,i);
            }
        }
    }

    public void complete( Player player, Characteristics characteristics, double Pm) {
        Random random = new Random(System.currentTimeMillis());
        double j;
        j = random.nextDouble();
        if( j < Pm ) {
            for(int i = 0; i < 6; i++) {
                mutatePlayer(player, characteristics ,i);
            }
        }
    }

    private void mutatePlayer( Player player, Characteristics characteristics, int index ) {
        Item selectedItem;
        Random random = new Random(System.currentTimeMillis());

        switch (index) {
            case 0:
                selectedItem = characteristics.getWeapons().get(random.nextInt(characteristics.getWeapons().size()));
                player.setByIndex(1, selectedItem);
                break;
            case 1:
                selectedItem = characteristics.getBoots().get(random.nextInt(characteristics.getBoots().size()));
                player.setByIndex(2, selectedItem);
                break;
            case 2:
                selectedItem = characteristics.getGloves().get(random.nextInt(characteristics.getGloves().size()));
                player.setByIndex(3, selectedItem);
                break;
            case 3:
                selectedItem = characteristics.getHelmets().get(random.nextInt(characteristics.getHelmets().size()));
                player.setByIndex(4, selectedItem);
                break;
            case 4:
                selectedItem = characteristics.getArmors().get(random.nextInt(characteristics.getArmors().size()));
                player.setByIndex(5, selectedItem);
                break;
            case 5:
                player.setHeight(random.nextDouble()*(2-1.3) + 1.3);
                break;
        }
    }
}
