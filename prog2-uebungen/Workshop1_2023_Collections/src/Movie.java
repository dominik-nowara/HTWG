import java.util.Arrays;
import java.util.List;

public record Movie(String title, List<String> actors, int year) {
    public Movie(String s) {
        this(parseTitle(s),parseActors(s),parseYear(s));
    }

    private static String parseTitle(String s) {
        int klAuf = s.indexOf(" (");
        return s.substring(0, klAuf);
    }

    private static int parseYear(String s) {
        int klAuf = s.indexOf(" (");
        int klZu = s.indexOf(")");
        return Integer.parseInt(s.substring(klAuf + 2, klZu));
    }

    private static List<String> parseActors(String s) {
        int klZu = s.indexOf(")");
        return Arrays.asList(s.substring(klZu+2).split("/"));
    }
}
