package TP2.RPG.Player;

public enum Classes {
    WARRIOR(0.6, 0.6),
    ARCHER(0.9,0.1),
    DEFENDER(0.3,0.8),
    ROGUE(0.8,0.3);

    private final double defense, attack;

    Classes(double attack, double defense) {
        this.attack = attack;
        this.defense = defense;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }
}
