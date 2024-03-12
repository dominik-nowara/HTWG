// O. Bittel
// 10.03.2017

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelefonBuch {

    private TreeMap<String,String> telBuch = new TreeMap<String,String>();

    public boolean insert(String name, String zusatz, String telNr) {
        String formatedName = (!zusatz.isEmpty()) ? name + " " + zusatz : name;

        if (telBuch.containsKey(formatedName) || telBuch.containsValue(telNr))
            return false;

        telBuch.put(formatedName, telNr);
        return true;
    }

    public boolean remove(String name, String zusatz) {
        String formatedName = (!zusatz.isEmpty()) ? name + " " + zusatz : name;

        if (!telBuch.containsKey(formatedName))
            return false;

        try {
            telBuch.remove(formatedName);
        }
        catch (Exception exception) {
            return false;
        }

        return true;
    }

    public String exactSearch(String name, String zusatz) {
        String formatedName = (!zusatz.isEmpty()) ? name + " " + zusatz : name;

        if (telBuch.containsKey(formatedName)) {
            return formatedName + " " + telBuch.get(formatedName);
        }

        return "";
    }

    public List<String> prefixSearch(String s) {
        List<String> results = new ArrayList<>();
        s = s.toLowerCase();

        for (Entry<String, String> e: telBuch.entrySet()) {
           String key = e.getKey();

           if (key.toLowerCase().startsWith(s)) {
               String value = e.getValue();
               results.add(key + " " + value);
           }
        }

        if (!results.isEmpty())
            return results;

        return null;
    }

    public void read(File f) {
        LineNumberReader in = null;
        try {
            telBuch.clear();
            in = new LineNumberReader(new FileReader(f));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                if (sf.length == 2) {
                    insert(sf[0], "", sf[1]); // leerer Zusatz
                } else if (sf.length == 3) {
                    insert(sf[0], sf[1], sf[2]);
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(File f) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(f);
            for (Entry<String, String> eintrag : telBuch.entrySet()) {
                String s = eintrag.getKey().replace('#', ' ') + " " + eintrag.getValue();
                out.println(s);
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void print(List<String> strList) {
        for (String s : strList)
            System.out.println(s);
    }

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        
        TelefonBuch telBuch = new TelefonBuch();
        telBuch.read(new File("C:\\Users\\Tenta\\Documents\\HTWG\\prog2-uebungen\\aufgabe9\\src\\TelBuchMit420Namen.txt"));

        System.out.println(telBuch.exactSearch("Oliver",""));
        System.out.println();

        print(telBuch.prefixSearch("H"));
        System.out.println();
        
        print(telBuch.prefixSearch(""));
        System.out.println();

        telBuch.insert("Oliver","1","33245");
        telBuch.insert("Oliver","2","23423");
        telBuch.insert("Oliver","3","87655");
        telBuch.remove("Oliver","2");

        print(telBuch.prefixSearch("Ol"));
        System.out.println();
       
        telBuch.save(new File("test.txt"));
    }
}

