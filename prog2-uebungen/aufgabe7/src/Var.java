import java.util.Map;
import java.util.Set;

public class Var implements Expression {
    public final String value;

    public Var(String value) {
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> values) {
        return 0;
    }

    @Override
    public Set<String> getVars() {
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
