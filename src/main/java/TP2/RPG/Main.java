package TP2.RPG;

import TP2.RPG.Player.Item;
import TP2.RPG.Player.ItemType;
import TP2.RPG.Player.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Main {

    public static void main( String[] args ) {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("configs.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            int MAX_LINES = ((Long) jsonObject.get("maxLines")).intValue();
            Characteristics characteristics = new Characteristics(
                    jsonObject.get("playerClass").toString(),
                    parseFile(jsonObject.get("weaponsPath").toString(), MAX_LINES, ItemType.WEAPON),
                    parseFile(jsonObject.get("helmetsPath").toString(), MAX_LINES, ItemType.HELMET),
                    parseFile(jsonObject.get("glovesPath").toString(), MAX_LINES, ItemType.GLOVES),
                    parseFile(jsonObject.get("armorsPath").toString(), MAX_LINES, ItemType.ARMOR),
                    parseFile(jsonObject.get("bootsPath").toString(), MAX_LINES, ItemType.BOOTS), 1.6);
            String firstSelectionMethod, secondSelectionMethod, firstReplacementMethod,
                    secondReplacementMethod, crossover, mutation, implementation, evaluator;
            firstSelectionMethod = jsonObject.get("firstSelectionMethod").toString();
            secondSelectionMethod = jsonObject.get("secondSelectionMethod").toString();
            firstReplacementMethod = jsonObject.get("firstReplacementMethod").toString();
            secondReplacementMethod = jsonObject.get("secondReplacementMethod").toString();
            crossover = jsonObject.get("crossover").toString();
            mutation = jsonObject.get("mutation").toString();
            implementation = jsonObject.get("implementation").toString();
            evaluator = jsonObject.get("evaluator").toString();
            double A = (double) jsonObject.get("A"), B = (double) jsonObject.get("B"), Pm = (double) jsonObject.get("Pm");
            int N = ((Long) jsonObject.get("N")).intValue(), K = ((Long) jsonObject.get("K")).intValue(), M = ((Long) jsonObject.get("deterministicM")).intValue();
            int initialPopulation = ((Long) jsonObject.get("initialPopulation")).intValue(), maxGenerations = ((Long) jsonObject.get("maxGenerations")).intValue();
            long maxMillis = (long) jsonObject.get("maxMillis");
            int acceptableSolution = ((Long)jsonObject.get("acceptableSolution")).intValue();

            Resolver resolver = new Resolver();
            List<Player> winners = resolver.solve(characteristics, K, M, firstSelectionMethod, crossover, mutation,
                    evaluator, maxMillis, Pm, implementation, firstReplacementMethod, N, maxGenerations,
                    initialPopulation, A, B, secondSelectionMethod, secondReplacementMethod, ((Long) jsonObject.get("contentMaxRounds")).intValue(),
                    (double) jsonObject.get("structureDiversity") ,(double) jsonObject.get("delta"), acceptableSolution, (Double) jsonObject.get("T0"), (Double) jsonObject.get("Tc") );
        } catch (IOException | ParseException e) {
            System.out.println("There was an error reading the JSON file");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static List<Item> parseFile(String file, long maxLines, ItemType type ) throws IOException {
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
            scanner.useDelimiter("\\t|\\s|;");
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
