public enum Classes {
    warrior(0.6, 0.6),
    archer(0.9,0.1),
    defender(0.3,0.8),
    rogue(0.8,0.3);

    private double attack;
    private double defense;

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
