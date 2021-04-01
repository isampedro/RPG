public class EvaluateTime implements Evaluator{
    @Override
    public boolean evaluate(long startTime, long maxMillis) {
        return System.currentTimeMillis() - startTime < maxMillis;
    }
}
