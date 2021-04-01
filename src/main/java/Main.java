import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main( String[] args ) throws IOException {
        int MAX_LINES = 100000;
        Characteristics characteristics = new Characteristics(args[0],
                parseFile("allitems/armas.tsv", MAX_LINES, ItemType.WEAPON),
                parseFile("allitems/cascos.tsv", MAX_LINES, ItemType.HELMET),
                parseFile("allitems/guantes.tsv", MAX_LINES, ItemType.GLOVES),
                parseFile("allitems/pecheras.tsv", MAX_LINES, ItemType.ARMOR),
                parseFile("allitems/botas.tsv", MAX_LINES, ItemType.BOOTS), 1.6);
        Resolver resolver = new Resolver();
        List<Player> winners = resolver.solve(characteristics, Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], args[4], args[5],
                args[6], Long.parseLong(args[7]), Double.parseDouble(args[8]), args[9], args[10], Integer.parseInt(args[11]), Integer.parseInt(args[12]), Integer.parseInt(args[13]) );
        for (Player winner : winners) {
//            System.out.println("Player:\n" + winner);
//            System.out.println("Performance: " + winner.getPerformance());
//            System.out.println("Generation: " + winner.getGeneration());
        }
//        System.out.println("All done!");
    }

    private static List<Item> parseFile(String file, int maxLines, ItemType type ) throws IOException {
        List<Item> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line, attribute; Scanner scanner;
        double strength=0, agility=0, expertise=0, resistance=0, vitality=0;
        int index, id = 0, lines = 0;
        reader.readLine();
        while( (line = reader.readLine()) != null && lines < maxLines ) {
            index = 0;
            scanner = new Scanner(line);
            scanner.useDelimiter("\\t|\\s");
            while (scanner.hasNext()) {
                attribute = scanner.next();
                switch (index) {
                    case 0:
                        id = Integer.parseInt(attribute);
                    case 1:
                        strength = Double.parseDouble(attribute);
                        break;
                    case 2:
                        agility = Double.parseDouble(attribute);
                        break;
                    case 3:
                        expertise = Double.parseDouble(attribute);
                        break;
                    case 4:
                        resistance = Double.parseDouble(attribute);
                        break;
                    case 5:
                        vitality = Double.parseDouble(attribute);
                        break;
                }
                index++;
            }
            list.add( new Item(strength, agility, expertise, resistance, vitality, type));
            lines++;
        }
        reader.close();
        fileReader.close();
        return list;
    }
}
