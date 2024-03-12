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

    JDialog messageDialog;

    public TelefonBuchSuchenLoeschenPanel(TelefonBuch tb) {
        telBuch = tb;

        messageDialog = new JDialog();

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

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(labelName);
        panel1.add(labelZusatz);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        panel2.add(fieldName);
        panel2.add(fieldZusatz);

        JPanel panelSuche = new JPanel();
        Border suchenBorder = BorderFactory.createTitledBorder("Suchen/Löschen");
        panelSuche.setBorder(suchenBorder);
        panelSuche.add(panel1);
        panelSuche.add(panel2);
        panelSuche.add(comboBox);
        panelSuche.add(buttonAnwenden);

        JPanel panelAusgabe = new JPanel();
        Border ausgabeBorder = BorderFactory.createTitledBorder("Ausgabe");
        panelAusgabe.setBorder(ausgabeBorder);

        textArea = new JTextArea(10, 45);
        panelAusgabe.add(textArea);
        panelAusgabe.setMinimumSize(new Dimension(0, 600));

        this.add(panelSuche, BorderLayout.NORTH);
        this.add(panelAusgabe, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedIndex() == 0) {
            if (fieldName.getText().isEmpty()) return;

            String text = "";

            if (fieldZusatz.getText().isEmpty())
                text = telBuch.exactSearch(fieldName.getText(), "");
            else
                text = telBuch.exactSearch(fieldName.getText(), fieldZusatz.getText());

            if (text.isEmpty()){
                JOptionPane.showMessageDialog(null,"Dieser Eintrag konnte nicht gefunden werden.",
                        "Nicht gefunden",2);
                return;
            }

            textArea.setText(text);
        }
        else if (comboBox.getSelectedIndex() == 1) {
            if (fieldName.getText().isEmpty()) return;

            List<String> prefixTexts = telBuch.prefixSearch(fieldName.getText());
            StringBuilder stringBuilder = new StringBuilder();

            if (prefixTexts == null) {
                JOptionPane.showMessageDialog(null,"Die Einträge mit dem Prefix konnten nicht gefunden werden.",
                        "Nicht gefunden",2);
                return;
            }

            for (String prefixText : prefixTexts) {
                stringBuilder.append(prefixText).append(System.lineSeparator());
            }

            textArea.setText(stringBuilder.toString());
        }
        else {
            if (fieldName.getText().isEmpty()) return;

            boolean wasRemoved = false;

            if (fieldZusatz.getText().isEmpty())
                wasRemoved = telBuch.remove(fieldName.getText(), "");
            else
                wasRemoved = telBuch.remove(fieldName.getText(), fieldZusatz.getText());

            if (wasRemoved) {
                JOptionPane.showMessageDialog(null,"Der Eintrag wurde erfolgreich entfernt.",
                        "Entfernt",1);
            }
            else {
                JOptionPane.showMessageDialog(null,"Der Eintrag konnte nicht entfernt werden",
                        "Fehler",3);
            }
        }
    }
}
