import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Var implements Expression {
    public final String value;

    public Var(String value) {
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> values) {
        return values.get(value);
    }

    @Override
    public Set<String> getVars() {
        Set<String> set = new HashSet<>();
        set.add(value);
        return set;
    }

    @Override
    public String toString() {
        return value;
    }
}
