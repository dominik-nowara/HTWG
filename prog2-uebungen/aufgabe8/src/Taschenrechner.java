import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Taschenrechner extends JFrame implements ActionListener, ItemListener {

    JTextField textField1;
    JTextField textField2;
    JTextField textFieldResolution;

    JCheckBox buttonColor;
    JRadioButton buttonDegree;
    JRadioButton buttonRadius;

    JButton buttonPlus;
    JButton buttonMultiply;
    JButton buttonMinus;
    JButton buttonDevide;
    JButton buttonSinus;
    JButton buttonCosinus;
    JButton buttonPotence;
    JButton buttonLog2;
    JButton buttonClear;

    boolean isRad;
    boolean isDark;

    public Taschenrechner() {
        this.setTitle("Taschenrechner");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 300));

        JLabel label1 = new JLabel("Operand x");
        JLabel label2 = new JLabel("Operand y");
        JLabel labelResolution = new JLabel("Resultat");

        textField1 = new JTextField("0",10);
        textField2 = new JTextField("0",10);
        textFieldResolution = new JTextField("0",10);
        textFieldResolution.setEditable(false);

        buttonColor = new JCheckBox("Helles Display");
        buttonColor.setSelected(true);

        buttonDegree = new JRadioButton("Deg");
        buttonDegree.setSelected(true);

        buttonRadius = new JRadioButton("Rad");

        ButtonGroup group = new ButtonGroup();
        group.add(buttonDegree);
        group.add(buttonRadius);

        buttonDegree.addItemListener(this);
        buttonRadius.addItemListener(this);
        buttonColor.addItemListener(this);

        buttonPlus = new JButton("+");
        buttonMultiply = new JButton("*");
        buttonMinus = new JButton("-");
        buttonDevide = new JButton("/");
        buttonSinus = new JButton("sin");
        buttonCosinus = new JButton("cos");
        buttonPotence = new JButton("x^y");
        buttonLog2 = new JButton("log2");
        buttonClear = new JButton("Clear");

        buttonPlus.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonDevide.addActionListener(this);
        buttonSinus.addActionListener(this);
        buttonCosinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonLog2.addActionListener(this);
        buttonPotence.addActionListener(this);
        buttonClear.addActionListener(this);

        JPanel panelIO = new JPanel();
        panelIO.setPreferredSize(new Dimension(450, 100));
        panelIO.setBorder(BorderFactory.createLineBorder(Color.gray));
        panelIO.setLayout(new GridLayout(3, 2));
        panelIO.add(label1);
        panelIO.add(textField1);
        panelIO.add(label2);
        panelIO.add(textField2);
        panelIO.add(labelResolution);
        panelIO.add(textFieldResolution);

        JPanel panelAuswahl = new JPanel();
        panelAuswahl.setLayout(new GridLayout(1, 3));
        panelAuswahl.add(buttonDegree);
        panelAuswahl.add(buttonRadius);
        panelAuswahl.add(buttonColor);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(450, 75));
        panelButton.setBorder(BorderFactory.createLineBorder(Color.gray));
        panelButton.setLayout(new GridLayout(2, 4));
        panelButton.add(buttonPlus);
        panelButton.add(buttonMultiply);
        panelButton.add(buttonMinus);
        panelButton.add(buttonDevide);
        panelButton.add(buttonSinus);
        panelButton.add(buttonCosinus);
        panelButton.add(buttonPotence);
        panelButton.add(buttonLog2);

        JPanel panelClear = new JPanel();
        panelClear.add(buttonClear);

        this.setLayout(new FlowLayout());
        this.add(panelIO);
        this.add(panelAuswahl);
        this.add(panelButton);
        this.add(panelClear);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Taschenrechner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String s1 = textField1.getText();
        String s2 = textField2.getText();

        double o1 = 0;
        double o2 = 0;

        try {
            o1 = Double.parseDouble(s1.replaceAll(",", "."));
            o2 = Double.parseDouble(s2.replaceAll(",", "."));
        } catch (Exception x) {
            if (source != buttonClear) {
                System.out.println("Fehlereingabe!");
                source = null;
            }
        }

        if (source == buttonPlus) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f", o1 + o2));
        }
        else if (source == buttonMultiply) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f",o1*o2));
        }
        else if (source == buttonMinus) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f",o1-o2));
        }
        else if (source == buttonDevide) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f",o1/o2));
        }
        else if (source == buttonSinus) {
            if (isRad)
                textFieldResolution.setText(String.format(Locale.US, "%.2f", Math.sin(o1)));
            else
                textFieldResolution.setText(String.format(Locale.US, "%.2f", Math.sin(Math.toRadians(o1))));

            textField2.setText("0");
        }
        else if (source == buttonCosinus) {
            if (isRad)
                textFieldResolution.setText(String.format(Locale.US, "%.2f", Math.cos(o1)));
            else
                textFieldResolution.setText(String.format(Locale.US, "%.2f", Math.cos(Math.toRadians(o1))));

            textField2.setText("0");
        }
        else if (source == buttonPotence) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f", Math.pow(o1, o2)));
        }
        else if (source == buttonLog2) {
            textFieldResolution.setText(String.format(Locale.US, "%.2f",Math.log(o1) / Math.log(2)));
            textField2.setText("0");
        }
        else if (source == buttonClear) {
            textField1.setText("0");
            textField2.setText("0");
            textFieldResolution.setText("0");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();

        if (source == buttonColor) {
            if (isDark) {
                textField1.setBackground(Color.white);
                textField2.setBackground(Color.white);
                textFieldResolution.setBackground(Color.white);

                textField1.setForeground(Color.black);
                textField2.setForeground(Color.black);
                textFieldResolution.setForeground(Color.black);

                isDark = false;
            } else {
                textField1.setBackground(Color.black);
                textField2.setBackground(Color.black);
                textFieldResolution.setBackground(Color.black);

                textField1.setForeground(Color.yellow);
                textField2.setForeground(Color.yellow);
                textFieldResolution.setForeground(Color.yellow);

                isDark = true;
            }
        }
        else if (source == buttonDegree)
            isRad = false;
        else if (source == buttonRadius)
            isRad = true;
    }
}