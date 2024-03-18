package aufgabe6;

import java.io.PrintWriter;

/***
 * Formatiert die Notenliste in ein HTML Dokument.
 */
public final class HtmlFormat {
    private HtmlFormat() { }

    /**
     * Speicher unsere Leistungen als Datei.
     * @param ausgabe PrintWriter, welcher die Datei erstellt
     * @param name Name der jeweiligen Person
     * @param leistungen Liste mit Leistungen
     */
    public static void ausgeben(PrintWriter ausgabe,
                                String[] name, LeistungsListe leistungen) {
        ausgabe.println("<!DOCTYPE html>\n"
                + "<html lang=\"de\">\n"
                + "<head>\n"
                + "<meta charset=\"utf-8\">\n"
                + "<meta name=\"viewport\" "
                + "content=\"width=device-width, initial-scale=1.0\">\n"
                + "<style>\n"
                + "table { width:100%; }\n"
                + "th { text-align:left; }\n"
                + "</style>\n"
                + "<title>Notenspiegel</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Notenspiegel für ");

        for (String n : name) {
            ausgabe.print(n + " ");
        }
        ausgabe.println("</h1>\n"
                + "<hr>\n"
                + "<table>\n"
                + "<tr><th>Fach:</th><th>Art:</th><th>Note:</th></tr>");

        for (Leistung n : leistungen) {
            if (n.istBestanden()) {
                ausgabe.println("<tr>"
                        + "<td>" + n.getFach() + "</td>");
            } else {
                ausgabe.println("<tr>"
                        + "<td style=\"color:red\">" + n.getFach() + "</td>");
            }
            if (n instanceof BenoteteLeistung) {
                ausgabe.println("<td>L</td>");
                ausgabe.println("<td>" + n.getNote() + "</td></tr>");
            } else if (n instanceof UnbenoteteLeistung) {
                ausgabe.println("<td>S</td>");
                ausgabe.println("<td>" + n.getNoteInWorten() + "</td></tr>");
            }

        }

        ausgabe.println("</table>\n"
                + "<hr>\n"
                + "L = Leistungsnachweis, S = Schein\n"
                + "</body>\n"
                + "</html>");

    }

    private static String formatted() {
        return "";
    }
}
