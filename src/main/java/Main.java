import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main( String[] args ) throws IOException {
        Player attacker = new Player("warrior", 2);
        List<Item> weapons = new ArrayList<Item>(), boots = new ArrayList<Item>(), helmets = new ArrayList<Item>(), gloves = new ArrayList<Item>(),
                armors = new ArrayList<Item>();
        parseFile("allitems/guantes.tsv", weapons);
        parseFile("allitems/botas.tsv", boots);
        parseFile("allitems/armas.tsv", helmets);
        parseFile("allitems/cascos.tsv", gloves);
        parseFile("allitems/pecheras.tsv", armors);
        System.out.println("All done");
    }

    private static void parseFile(String file, List<Item> list ) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line, attribute; Scanner scanner;
        double strength=0, agility=0, expertise=0, resistance=0, vitality=0;
        int index, id = 0;
        reader.readLine();
        Item item;
        while( (line = reader.readLine()) != null ) {
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
            list.add( new Item(id, strength, agility, expertise, resistance, vitality));
        }
        reader.close();
        fileReader.close();
    }
}
