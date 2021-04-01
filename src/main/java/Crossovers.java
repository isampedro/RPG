import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Crossovers {
    public static List<Player> singlePoint(Player p1, Player p2, Random random) {
        int listValue;
        List<Player> children = new ArrayList<>();

        listValue = random.nextInt(6);

        switch (listValue+1) {
            case 1:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p1.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                break;
            case 2:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                break;
            case 3:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p1.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p2.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                break;
            case 4:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                break;
            case 5:
                children.add(new Player(p1.getPlayerClass(), p1.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p2.getPlayerClass(), p2.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                break;
            case 6:
                children.add(new Player(p1.getPlayerClass(), p2.getHeight(), p2.getWeapon(), p2.getBoots(), p2.getHelmet(), p2.getGloves(), p2.getArmor(), p1, p1.getGeneration() + 1));
                children.add(new Player(p2.getPlayerClass(), p1.getHeight(), p1.getWeapon(), p1.getBoots(), p1.getHelmet(), p1.getGloves(), p1.getArmor(), p1, p1.getGeneration() + 1));
                break;
        }

        return children;
    }

    public static List<Player> doublePoint(Player p1, Player p2, Random random) {
        int rand1, rand2, aux;
        double auxHeight;
        List<Player> children = new ArrayList<>();
        Player child1 = new Player(p1, p1.getGeneration() + 1), child2 = new Player(p2, p2.getGeneration() + 1);
        Item auxItem;

        rand1 = random.nextInt(6);
        rand2 = random.nextInt(6);
        if( rand1 > rand2 ) {
            aux = rand1;
            rand1 = rand2;
            rand2 = aux;
        }

        for( int i = rand1 + 1; i <= rand2 + 1; i++) {
            if( i == 6 ) {
                auxHeight = child1.getHeight();
                child1.setHeight(child2.getHeight());
                child2.setHeight(auxHeight);
            } else {
                auxItem = child1.getByIndex(i);
                child1.setByIndex(i, child2.getByIndex(i));
                child2.setByIndex(i, auxItem);
            }
        }
        children.add(child1);
        children.add(child2);

        return children;
    }

    public static List<Player> annular(Player p1, Player p2, Random random) {
        int index, L;
        double auxHeight;
        List<Player> children = new ArrayList<>();
        Player child1 = new Player(p1, p1.getGeneration() + 1), child2 = new Player(p2, p2.getGeneration() + 1);
        Item auxItem;

        index = random.nextInt(6);
        L = random.nextInt(3) + 1;

        for (int i = 1; i <= L; i++) {
            if ((index + i) % 6 == 0) {
                auxHeight = child1.getHeight();
                child1.setHeight(child2.getHeight());
                child2.setHeight(auxHeight);
            } else {
                auxItem = child1.getByIndex((index + i) % 6);
                child1.setByIndex((index + i) % 6, child2.getByIndex((index + i) % 6));
                child2.setByIndex((index + i) % 6, auxItem);
            }
        }
        children.add(child1);
        children.add(child2);

        return children;
    }

    public static List<Player> uniform(Player p1, Player p2, Random random) {
        boolean rand;
        double auxHeight;
        List<Player> children = new ArrayList<>();
        Player child1 = new Player(p1, p1.getGeneration() + 1), child2 = new Player(p2, p2.getGeneration() + 1);
        Item auxItem;

        for (int i = 1; i <= 6; i++) {
            rand = random.nextBoolean();
            if (i == 6 && rand) {
                auxHeight = child1.getHeight();
                child1.setHeight(child2.getHeight());
                child2.setHeight(auxHeight);
            } else if( i < 6 && rand){
                auxItem = child1.getByIndex(i);
                child1.setByIndex(i, child2.getByIndex(i));
                child2.setByIndex(i, auxItem);
            }
        }
        children.add(child1);
        children.add(child2);

        return children;
    }
}
