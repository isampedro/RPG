public class Item {
    private final double strength, agility, expertise, resistance, vitality;
    private final int id;

    public Item(int id, double strength, double agility, double expertise, double resistance, double vitality) {
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.vitality = vitality;
        this.id = id;
    }

    public int getId() {
        return id;
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
