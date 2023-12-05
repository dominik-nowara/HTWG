import java.util.Map;
import java.util.Set;

public interface Expression {
    double eval(Map<String, Double> values);
    Set<String> getVars();
}
