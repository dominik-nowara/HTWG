/*
 * class Tokenizer
 * Zerlegt einen String in eine Folge von Tokens.
 * Tokens: +, *, , (, ), $ oder Gleitkommazahl (ohne Vorzeichen und Exponent).
 * ^ oist der Potenzoperator.
 * Gleitkommazahlen können  mit Dezimalpunkt (z.B. 3.24 statt 3,24) eingegeben werden
 *
 * O. Bittel; 24.2.2019
 */
package expressionevaluation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author oliverbittel
 */
public class Tokenizer {

    public final static String KL_AUF = "(";
    public final static String KL_ZU = ")";
    public final static String PLUS = "+";
    public final static String MULT = "*";
    public final static String POWER = "^";
    public final static String DOLLAR = "$";

    public final static String[] KEYWORDS = {KL_AUF, KL_ZU, PLUS, MULT, POWER, DOLLAR};

    private final Scanner in;

    /**
     *
     * @param s String, der in eine Folge von Tokens zerlegt werden soll.
     */
    public Tokenizer(String s) {
        s = s.replaceAll("\\(", " ( ");
        s = s.replaceAll("\\)", " ) ");
        s = s.replaceAll("\\+", " + ");
        s = s.replaceAll("\\*", " * ");
        s = s.replaceAll("\\^", " ^ ");
        s += " $";
        //System.out.println(s);

        in = new Scanner(s);

        // Use "." as decimal separator:
        // System.out.println(Locale.getDefault());
        in.useLocale(Locale.US);

        // NumberFormat f = NumberFormat.getInstance(in.locale());
        // if (f instanceof DecimalFormat) {
        //    System.out.println("Used decimal separator: " + ((DecimalFormat) f).getDecimalFormatSymbols().getDecimalSeparator());
        // }
    }

    /**
     * Liefert den nächsten Token.
     *
     * @return String aus KEYWORDS oder double-Wert, falls das nächste Token
     * eine Gleitkommazahl ist; null bei Fehler. Bei String-Ende wird "$"
     * zurückgeliefert.
     */
    public Object nextToken() {
        if (!in.hasNext()) {
            return null; // Eingabeende
        }
        if (in.hasNextDouble()) {
            return in.nextDouble();
        }
        String token = in.next();
        for (String t : KEYWORDS) {
            if (t.equals(token)) {
                return t;	// String-Konstante wird zurückgeliefert!
            }
        }
        System.out.println("Error: wrong token");
        return null;
    }

    // Zu Testzwecken:
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "2^3+3.2*4.5+4";
        Tokenizer tokenizer = new Tokenizer(s);
        Object token;
        while ((token = tokenizer.nextToken()) != null) {
            System.out.println(token);
        }
    }
}
