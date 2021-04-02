package RPG.Player;

public class Player {
    private final Classes playerClass;
    private double height;
    private Item weapon, boots, helmet, gloves, armor;
    private Player father;
    private int generation;

    public Player(Player player, int generation) {
        this.playerClass = player.playerClass;
        this.height = player.height;
        this.weapon = player.weapon;
        this.boots = player.boots;
        this.helmet = player.helmet;
        this.gloves = player.gloves;
        this.armor = player.armor;
        this.father = player;
        this.generation = generation;
    }

    public Player(Classes playerClass, int generation) {
        this.playerClass = playerClass;
        weapon = new Item(0,0,0,0,0,ItemType.WEAPON);
        boots = new Item(0,0,0,0,0,ItemType.BOOTS);
        helmet = new Item(0,0,0,0,0,ItemType.HELMET);
        gloves = new Item(0,0,0,0,0,ItemType.GLOVES);
        armor = new Item(0,0,0,0,0,ItemType.ARMOR);
        height = 1.3;
        this.generation = generation;
    }

    public Player(Classes playerClass, double height, Item weapon, Item boots, Item helmet, Item gloves, Item armor, int generation) {
        this.playerClass = playerClass;
        this.height = height;
        this.weapon = weapon;
        this.boots = boots;
        this.helmet = helmet;
        this.gloves = gloves;
        this.armor = armor;
        this.generation = generation;
    }

    public Player(Classes playerClass, double height, Item weapon, Item boots, Item helmet, Item gloves, Item armor, Player father, int generation) {
        this.playerClass = playerClass;
        this.height = height;
        this.weapon = weapon;
        this.boots = boots;
        this.helmet = helmet;
        this.gloves = gloves;
        this.armor = armor;
        this.father = father;
        this.generation = generation;
    }

    public Player getFather() {
        return father;
    }

    public Classes getPlayerClass() {
        return playerClass;
    }

    public double getHeight() {
        return height;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Item getBoots() {
        return boots;
    }

    public Item getHelmet() {
        return helmet;
    }

    public Item getGloves() {
        return gloves;
    }

    public Item getArmor() {
        return armor;
    }

    public int getGeneration() {
        return generation;
    }

    public Player putWeapon(Item weapon ) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public Player putBoots( Item boots ) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public Player putHelmet( Item helmet ) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public Player putGloves( Item gloves ) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public Player putArmor( Item armor ) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public double getStrength() {
        double itemsStrength = weapon.getStrength() + boots.getStrength() + gloves.getStrength() + armor.getStrength() + helmet.getStrength();
        return 100*Math.tanh(0.01*itemsStrength);
    }

    public double getAgility() {
        double itemsAgility = weapon.getAgility() + boots.getAgility() + gloves.getAgility() + armor.getAgility() + helmet.getAgility();
        return Math.tanh(0.01*itemsAgility);
    }

    public double getExpertise() {
        double itemsExpertise = weapon.getExpertise() + boots.getExpertise() + gloves.getExpertise() + armor.getExpertise() + helmet.getExpertise();
        return 0.6*Math.tanh(0.01*itemsExpertise);
    }

    public double getResistance() {
        double itemsResistance = weapon.getResistance() + boots.getResistance() + gloves.getResistance() + armor.getResistance() + helmet.getResistance();
        return Math.tanh(0.01*itemsResistance);
    }

    public double getVitality() {
        double itemsVitality = weapon.getVitality() + boots.getVitality() + gloves.getVitality() + armor.getVitality() + helmet.getVitality();
        return 100*Math.tanh(0.01*itemsVitality);
    }

    public double getATM() {
        return 0.7 - Math.pow(3*height - 5, 4) + Math.pow(3*height - 5, 2) + height/4;
    }

    public double getDEM() {
        return  1.9 + Math.pow(2.5*height - 4.16, 4) - Math.pow(2.5*height - 4.16,2) - 3*height/10;
    }

    public double getItemsAttack() {
        return (getAgility() + getExpertise())*getStrength()*getATM();
    }

    public double getItemsDefense() {
        return (getResistance() + getExpertise())*getVitality()*getDEM();
    }

    public double getPerformance() {
        return playerClass.getAttack()*getItemsAttack() + playerClass.getDefense()*getItemsDefense();
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Player putHeight(double height) {
        return new Player(playerClass, height, weapon, boots, helmet, gloves, armor, generation);
    }

    public Item getByIndex( int index ) {
        switch (index) {
            case 1: return weapon;
            case 2: return boots;
            case 3: return helmet;
            case 4: return gloves;
            case 5: return armor;
        }
        return null;
    }

    public void setByIndex( int index, Item item ) {
        switch (index) {
            case 1: weapon = item; break;
            case 2: boots = item; break;
            case 3: helmet = item; break;
            case 4: gloves = item; break;
            case 5: armor = item; break;
        }
    }

    @Override
    public String toString() {
        return "class " + playerClass +
                "\nheight " + height +
                "\nweapon " + weapon +
                "\nboots " + boots +
                "\nhelmet " + helmet +
                "\ngloves " + gloves +
                "\narmor " + armor +
                "\ngeneration " + generation + '\n';
    }
}
