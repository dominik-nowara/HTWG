import java.util.Map;

public class Quotient extends CompoundExpression {

    public Quotient(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public double eval(Map<String, Double> values) {
        firstValue = firstExp.eval(values);
        secondValue = secondExp.eval(values);
        return firstValue / secondValue;
    }

    @Override
    public String toString() {
        return this.toString(" / ");
    }
}