package TP2.RPG.Player;

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

    private boolean isSimilar( double itemAbility, double thisAbility, double delta ) {
        return itemAbility >= thisAbility - delta && itemAbility <= thisAbility + delta;
    }

    public boolean isSimilar( Item i, double delta ) {
        return isSimilar( i.strength, strength, delta ) && isSimilar( i.agility, agility, delta )
                && isSimilar( i.expertise, expertise, delta ) && isSimilar( i.resistance, resistance, delta )
                && isSimilar( i.vitality, vitality, delta );
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
        return "strength=" + strength + ", agility=" + agility + ", expertise=" + expertise + ", resistance=" + resistance + ", vitality=" + vitality;
    }
}
