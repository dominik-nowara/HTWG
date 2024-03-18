// O. Bittel
// 10.03.2017

import javax.swing.*;
import java.awt.*;

public class TelefonBuchGUI extends JFrame {

    private TelefonBuch telBuch;

    public TelefonBuchGUI() {
        // TelefonBuch anlegen:
        telBuch = new TelefonBuch();

        // Menuleiste einbauen:
        this.setJMenuBar(new TelefonBuchMenuBar(telBuch));

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(new TelefonBuchEinfuegenPanel(telBuch));
        mainPanel.add(new TelefonBuchSuchenLoeschenPanel(telBuch));
        this.setContentPane(mainPanel);

        // Sonstige Eigenschaften des Hauptfenster setzen:
        this.setTitle("Telefonbuch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TelefonBuchGUI();
    }
}
