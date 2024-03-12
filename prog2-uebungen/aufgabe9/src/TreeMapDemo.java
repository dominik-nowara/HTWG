// O. Bittel
// 10.03.2017

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;


public class TreeMapDemo {
    public static void main(String[] args) {
        NavigableMap<String,Integer> telBuch = new TreeMap<String,Integer>();

        // Kunden eintragen:
        telBuch.put("Maier", 1234);
        telBuch.put("Anton", 4567);
        telBuch.put("Meyer", 4711);
        telBuch.put("Mueller", 7890);
        telBuch.put("Vogel", 1357);
        telBuch.put("Baier", 2468);

        // TelNummer nachschlagen:
        Integer telNr;
        if ((telNr = telBuch.get("Vogel")) != null) {
            System.out.println("Vogel: " + telNr);
        }

        // TelNummer aendern:
        telBuch.put("Maier", 4321);

        // TelBuch sortiert ausgeben:
        System.out.println("Telefonbuch:");
        for (Entry<String,Integer> eintrag : telBuch.entrySet()) {
            System.out.println(eintrag.getKey() + ": " + eintrag.getValue());
        }

        // TelBuch - nur Kundenname ausgeben:
        System.out.println("Telefonbuch (nur Kundenname):");
        for (String kunde : telBuch.keySet()) {
            System.out.println(kunde);
        }

        // Bereichssichten: TelBuch nur mit 'M' ausgeben:
        System.out.println("Telefonbucheintaege mit M:");
        for (Entry<String,Integer> eintrag
                : telBuch.subMap("M", true, "N", false).entrySet()) {
            System.out.println(eintrag.getKey() + ": " + eintrag.getValue());
        }

    }
}
