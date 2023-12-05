import java.util.Map;

public class Quotient extends CompoundExpression {

    public Quotient(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public double eval(Map<String, Double> values) {
        super.eval(values);
        return firstValue / secondValue;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(firstExp.toString());
        stringBuilder.append(" / ");
        stringBuilder.append(secondExp.toString());
        return stringBuilder.append(")").toString();
    }
}