// O. Bittel
// 10.03.2017

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.List;

public class TelefonBuchSuchenLoeschenPanel
        extends JPanel implements ActionListener {
    TelefonBuch telBuch;

    JLabel labelName;
    JLabel labelZusatz;
    JTextField fieldName;
    JTextField fieldZusatz;
    JComboBox comboBox;
    JButton buttonAnwenden;

    JTextArea textArea;


    public TelefonBuchSuchenLoeschenPanel(TelefonBuch tb) {
        telBuch = tb;

        this.setLayout(new BorderLayout());

        labelName = new JLabel("Name");
        labelZusatz = new JLabel("Zusatz");

        fieldName = new JTextField("", 20);
        fieldZusatz = new JTextField("", 20);

        String[] comboList = { "Exakte Suche", "Prefix-Suche", "Löschen" };
        comboBox = new JComboBox(comboList);
        comboBox.setSelectedIndex(0);

        buttonAnwenden = new JButton("Anwenden");
        buttonAnwenden.addActionListener(this);

        textArea = new JTextArea(10, 45);
        textArea.setEditable(false);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(labelName);
        panel1.add(labelZusatz);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        panel2.add(fieldName);
        panel2.add(fieldZusatz);

        JPanel panelSuche = new JPanel();
        panelSuche.setLayout(new BoxLayout(panelSuche, BoxLayout.LINE_AXIS));
        Border suchenBorder = BorderFactory.createTitledBorder("Suchen/Löschen");
        panelSuche.setBorder(suchenBorder);
        panelSuche.add(panel1);
        panelSuche.add(panel2);
        panelSuche.add(comboBox);
        panelSuche.add(buttonAnwenden);

        JPanel panelAusgabe = new JPanel();
        panelAusgabe.setLayout(new BorderLayout());
        Border ausgabeBorder = BorderFactory.createTitledBorder("Ausgabe");
        panelAusgabe.setBorder(ausgabeBorder);
        panelAusgabe.add(textArea);

        this.add(panelSuche, BorderLayout.NORTH);
        this.add(panelAusgabe, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (fieldName.getText().isEmpty()) return;

        if (comboBox.getSelectedIndex() == 0) {
            String text = telBuch.exactSearch(fieldName.getText(), fieldZusatz.getText());

            if (text.isEmpty()){
                JOptionPane.showMessageDialog(this,"Dieser Eintrag konnte nicht gefunden werden.",
                        "Nicht gefunden",2);
                return;
            }

            textArea.setText(text);
        }
        else if (comboBox.getSelectedIndex() == 1) {
            List<String> prefixTexts = telBuch.prefixSearch(fieldName.getText());
            StringBuilder stringBuilder = new StringBuilder();

            if (prefixTexts == null) {
                JOptionPane.showMessageDialog(this,"Die Einträge mit dem Prefix konnten nicht gefunden werden.",
                        "Nicht gefunden",2);
                return;
            }

            for (String prefixText : prefixTexts) {
                stringBuilder.append(prefixText).append(System.lineSeparator());
            }

            textArea.setText(stringBuilder.toString());
        }
        else {
            boolean successful = telBuch.remove(fieldName.getText(), fieldZusatz.getText());

            if (successful) {
                JOptionPane.showMessageDialog(this,"Der Eintrag wurde erfolgreich entfernt.",
                        "Entfernt",1);
            }
            else {
                JOptionPane.showMessageDialog(this,"Der Eintrag konnte nicht entfernt werden",
                        "Fehler",3);
            }
        }
    }
}
