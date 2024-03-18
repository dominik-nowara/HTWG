import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constant implements Expression {
    public final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> values) {
        return value;
    }

    @Override
    public Set<String> getVars() {
        return new HashSet<>();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
