import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CompoundExpression implements Expression {
    public Expression firstExp;
    public Expression secondExp;

    public double firstValue = 0;
    public double secondValue = 0;

    public CompoundExpression(Expression first, Expression second) {
        this.firstExp= first;
        this.secondExp = second;
    }

    @Override
    public double eval(Map<String, Double> values) {
        if (firstExp instanceof Constant || firstExp instanceof Var)
            firstValue = getValue(firstExp, values);
        else
            firstValue = firstExp.eval(values);

        if (secondExp instanceof Constant || secondExp instanceof Var)
            secondValue = getValue(secondExp, values);
        else
            secondValue = secondExp.eval(values);

        return 0;
    }

    @Override
    public Set<String> getVars() {
        Set<String> set = new HashSet<>();

        if (firstExp instanceof Var) {
            Var variable = (Var) firstExp;
            set.add(variable.value);
        }
        else if (!(firstExp instanceof Constant)) {
            set.addAll(firstExp.getVars());
        }

        if (secondExp instanceof Var) {
            Var variable = (Var) secondExp;
            set.add(variable.value);
        }
        else if (!(secondExp instanceof Constant)) {
            set.addAll(secondExp.getVars());
        }

        return set;
    }

    private double getValue(Expression expression, Map<String, Double> values) {
        if (expression instanceof Constant) {
            Constant constant = (Constant) expression;
            return constant.value;
        }

        Var val = (Var) expression;
        return values.get(val.value);
    }
}
