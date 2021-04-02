package TP2.RPG.Player;

public class Pair {
    private final double relativeFitness;
    private final Player player;

    public Pair(double relativeFitness, Player player) {
        this.relativeFitness = relativeFitness;
        this.player = player;
    }

    public double getRelativeFitness() {
        return relativeFitness;
    }

    public Player getPlayer() {
        return player;
    }
}
