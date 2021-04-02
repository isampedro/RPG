package TP2.RPG.Evaluators;

public class EvaluateGenQ implements Evaluator{
    @Override
    public boolean evaluate(long startTime, long maxMillis, long actualGen, long maxGen) {
        return actualGen < maxGen;
    }
}
