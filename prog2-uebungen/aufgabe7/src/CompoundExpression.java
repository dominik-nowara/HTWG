import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CompoundExpression implements Expression {
    public final Expression firstExp;
    public final Expression secondExp;

    public double firstValue = 0;
    public double secondValue = 0;

    public CompoundExpression(Expression first, Expression second) {
        this.firstExp = first;
        this.secondExp = second;
    }

    @Override
    public Set<String> getVars() {
        Set<String> set = new HashSet<>();

        set.addAll(firstExp.getVars());
        set.addAll(secondExp.getVars());

        return set;
    }

    public String toString(String sign) {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(firstExp.toString());
        stringBuilder.append(sign);
        stringBuilder.append(secondExp.toString());
        return stringBuilder.append(")").toString();
    }
}
