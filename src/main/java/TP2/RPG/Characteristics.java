package TP2.RPG;

import TP2.RPG.Player.Classes;
import TP2.RPG.Player.Item;

import java.util.List;

public class Characteristics {
    private Classes playerClass;
    private double height;
    private List<Item> weapons, helmets, gloves, armors, boots;

    public Characteristics(String playerClass, List<Item> weapons, List<Item> helmets, List<Item> gloves, List<Item> armors, List<Item> boots, double height) {
        this.playerClass = Classes.valueOf(playerClass.toUpperCase());
        this.weapons = weapons;
        this.helmets = helmets;
        this.gloves = gloves;
        this.armors = armors;
        this.boots = boots;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public Classes getPlayerClass() {
        return playerClass;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    public List<Item> getHelmets() {
        return helmets;
    }

    public List<Item> getGloves() {
        return gloves;
    }

    public List<Item> getArmors() {
        return armors;
    }

    public List<Item> getBoots() {
        return boots;
    }
}
