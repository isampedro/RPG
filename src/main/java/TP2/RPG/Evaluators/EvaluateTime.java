package TP2.RPG.Evaluators;

public class EvaluateTime implements Evaluator{
    @Override
    public boolean evaluate(long startTime, long maxMillis, long evaluateTime, long maxGen) {
        return System.currentTimeMillis() - startTime < maxMillis;
    }
}
