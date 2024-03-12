// O. Bittel
// 10.03.2017

import javax.swing.*;
import java.io.*;
import java.awt.event.*;


public class TelefonBuchMenuBar
        extends JMenuBar implements ActionListener {

    private TelefonBuch telBuch;

    JMenu menu;
    JMenuItem menuItemLesen;
    JMenuItem menuItemSpeicher;
    JMenuItem menuItemBeenden;
    JFileChooser fileChooser;

    public TelefonBuchMenuBar(TelefonBuch tb) {
        telBuch = tb;

        menu = new JMenu("Datei");

        menuItemLesen = new JMenuItem("TelefonBuch lesen...");
        menuItemLesen.addActionListener(this);
        menu.add(menuItemLesen);

        menuItemSpeicher = new JMenuItem("TelefonBuch speichern...");
        menuItemSpeicher.addActionListener(this);
        menu.add(menuItemSpeicher);

        menu.addSeparator();

        menuItemBeenden = new JMenuItem("TelefonBuch beenden");
        menuItemBeenden.addActionListener(this);
        menu.add(menuItemBeenden);

        fileChooser = new JFileChooser();

        this.add(menu);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == menuItemLesen) {
            fileChooser.setDialogTitle("Datei zum öffnen wählen");
            int chooserResult = fileChooser.showOpenDialog(this);

            if (chooserResult == JFileChooser.APPROVE_OPTION) {
                telBuch.read(fileChooser.getSelectedFile());

                JOptionPane.showMessageDialog(this,"TelefonBuch erfolgreich geladen!",
                        "Geladen",1);
            }
        }
        else if (source == menuItemSpeicher) {
            fileChooser.setDialogTitle("Datei zum speichern wählen");
            int chooserResult = fileChooser.showSaveDialog(this);

            if (chooserResult == JFileChooser.APPROVE_OPTION) {
                telBuch.save(fileChooser.getSelectedFile());

                JOptionPane.showMessageDialog(this,"TelefonBuch erfolgreich gespeichert!",
                        "Gespeichert",1);
            }
        }
        else {
            int result = JOptionPane.showConfirmDialog(this,
                    "Beim beenden der Anwendung wird das Telefonbuch nicht gespeichert. Fortfahren?", "Anwendung beenden",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}

