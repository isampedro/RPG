public class Item {
    private final ItemType type;
    private final double strength, agility, expertise, resistance, vitality;

    public Item(double strength, double agility, double expertise, double resistance, double vitality, ItemType type) {
        this.type = type;
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.vitality = vitality;
    }

    public ItemType getType() {
        return type;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getResistance() {
        return resistance;
    }

    public double getVitality() {
        return vitality;
    }

    @Override
    public String toString() {
        return "Item strength=" + strength + ", agility=" + agility + ", expertise=" + expertise + ", resistance=" + resistance + ", vitality=" + vitality + '\n';
    }
}
