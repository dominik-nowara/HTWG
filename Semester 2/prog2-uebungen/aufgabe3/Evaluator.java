/*
 * class Evaluator
 * repl-Schleife: lese von der Konsole eine Ziele und 
 * werte sie als arithmetischen Ausdruck aus.
 * Da tokenizer String-Konstante zurückliefert, reicht 
 * Gleichheitsprüfung mit == aus (siehe z.B. shift-Methode).
 *
 * Ihr Name:
 * Datum: 
 */
package expressionevaluation;

import java.util.Scanner;
import static expressionevaluation.Tokenizer.*;

/**
 * Klasse zum Auswerten von arithmetischen Ausdrücken.
 */
public class Evaluator {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static Object[] stack = new Object[30];		// Stack
    private static int top = -1;					// Index des obersten Kellerelements
    private static Object token;					// Aktuelles Token
    private static Tokenizer tokenizer;				// Zerlegt String-Eingabe in Tokens

    /**
     * Wertet expr als arithmetischen Ausdruck aus.
     *
     * @param expr Arthmetischer Ausdruck als String
     * @return Wert des Ausdrucks oder null, falls der Ausdruck fehlerhaft ist.
     */
    public static Double eval(String expr) {
        // Dollar in leeren Stack ablegen:
        top = -1;
        stack[++top] = DOLLAR;

        // expr in Tokens zerlegen und erstes Token abholen:
        tokenizer = new Tokenizer(expr);
        token = tokenizer.nextToken();

        while (token != null) {
            if (accept())
                return (double) stack[top];

            if (!shift())
                return null;
        }
        return null;
    }

    private static boolean shift() {
        if (stack[top] == DOLLAR && (token == KL_AUF || isVal(token))) {		// Regel 1 der Parser-Tabelle
            doShift();
            return true;
        }
        else if (isOp(stack[top]) && (token == KL_AUF || isVal(token))) {       // Regel 2 der Parser-Tabelle
            doShift();
            return true;
        }
        else if (stack[top] == KL_AUF && (token == KL_AUF || isVal(token))) {   // Regel 3 der Parser-Tabelle
            doShift();
            return true;
        }
        else if (top >= 3 && stack[top] == KL_ZU && isVal(stack[top - 1])
                && stack[top - 2] == KL_AUF) {                                  // Regel 4 der Parser-Tabelle
            doReduceKlValKl();
            return true;
        }
        else if (top == 1 && isVal(stack[top]) && isOp(token)) {                 // Regel 6 der Parser-Tabelle
            doShift();
            return true;
        }
        else if (top >= 2 && isVal(stack[top]) && stack[top - 1] == KL_AUF      // Regel 7 der Parser-Tabelle
                && (token == KL_ZU || isOp(token))) {
            doShift();
            return true;
        }
        else if (top >= 3 && isVal(stack[top]) && isOp(stack[top - 1]) && isVal(stack[top - 2])
                && (token == KL_ZU || token == DOLLAR)) {                       // Regel 8 der Parser-Tabelle
            doReduceValOpVal();
            return true;
        }
        else if (top >= 3 && isVal(stack[top]) && isOp(stack[top - 1]) && isVal(stack[top - 2])
                && isOp(token)) {                                               // Regel 9 der Parser-Tabelle
            if (reduce()) {
                doReduceValOpVal();
            }
            else {
                doShift();
            }
            return true;
        }
        else {
            return false;
        }
    }

    private static void doShift() {
        stack[++top] = token;
        token = tokenizer.nextToken();
    }

    private static boolean isOp(Object o) {
        return (o == PLUS || o == MULT || o == POWER);
    }

    private static boolean isVal(Object o) {
        return o instanceof Double;
    }

    private static boolean reduce() {
        if (token == PLUS) {
            return true;
        }
        else if (token == MULT && (stack[top - 1] == MULT || stack[top - 1] == POWER)) {
            return true;
        }
        return false;
    }

    private static void doReduceKlValKl() {
        stack[top - 2] = stack[top - 1];
        stack[top - 1] = new Object();
        stack[top] = new Object();
        top -= 2;
    }

    private static void doReduceValOpVal() {
        if (stack[top - 1] == PLUS) {
            stack[top - 2] = (double) stack[top - 2] + (double) stack[top];
        }
        else if (stack[top - 1] == MULT) {
            stack[top - 2] = (double) stack[top - 2] * (double) stack[top];
        }
        else if (stack[top - 1] == POWER) {
            stack[top - 2] = Math.pow((double) stack[top - 2], (double) stack[top]);
        }

        stack[top - 1] = new Object();
        stack[top] = new Object();
        top -= 2;
    }

    private static boolean accept() {
        if (top == 1 && isVal(stack[top]) && token == DOLLAR) {            // Regel 5 der Parser-Tabelle
            return true;
        }
        return false;
    }

    /**
     * Liest von der Konsole eine Folge von Zeilen, wertet jede Zeile als
     * Ausdruck aus und gibt seinen Wert aus. (repl = read-evaluate-print-loop).
     */
    public static void repl() {
        Scanner in = new Scanner(System.in);
        System.out.print(ANSI_BLUE + ">> ");

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("end")) {
                System.out.println("bye!");
                return;
            }

            Double result = eval(line);

            if (result == null) {
                System.out.println("!!! error");
            }
            else {
                System.out.println(result);
            }

            System.out.print(ANSI_BLUE + ">> ");
        }
    }

    /**
     * Testprogramm.
     *
     * @param args wird nicht benutzt.
     */
    public static void main(String[] args) {
        // Zum Testen, später auskommentieren:
        /*
		String s1 = "1+2";
		String s2 = "2^10+5";
		String s3 = "5+2^10";
        String s4 = "(2+3*4+4)^2";
        String s5 = "(2+3*4+4))*2";
        String s6 = "2+3**4";
        String s7 = "2^3^2";
        String s8 = "2^2*5";
        String s9 = "1+(2+(3+(4+(5+6))))";
        String s10 = "1+2+3+4+5+6";

		System.out.println(eval(s1));	// 3.0
		System.out.println(eval(s2));	// 1029.0
        System.out.println(eval(s3));	// 1029.0
        System.out.println(eval(s4));	// 324.0
        //System.out.println(eval(s5));	// null; Syntaxfehler
        //System.out.println(eval(s6));	// null; Syntaxfehler
        System.out.println(eval(s7));	// 512.0
        System.out.println(eval(s8));	// 20.0
        System.out.println(eval(s9));	// 21.0
        System.out.println(eval(s10));	// 21.0*/

        repl();
    }
}
