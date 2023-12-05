import java.util.Map;
import java.util.Set;

public class Constant implements Expression {
    public final double value;

    public Constant(double value) {
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
        return String.valueOf(value);
    }
}
