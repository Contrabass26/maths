package answers;

import main.Tool;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Binary extends JPanel implements ActionListener {

    private final JTextField inputBox = new JTextField(); // Input box
    private final JLabel outputBox = new JLabel("0", SwingConstants.CENTER); // Output box
    // Mode switching buttons
    private final JButton bToDBtn = new JButton("Binary to decimal");
    private final JButton dToBBtn = new JButton("Decimal to binary");

    public Binary() {
        super();
        // Set layout
        setLayout(new GridBagLayout());
        Constraints c = new Constraints();
        // Binary to decimal button
        c.bToDBtn();
        bToDBtn.addActionListener(this);
        bToDBtn.setBackground(Tool.SELECTED);
        bToDBtn.setOpaque(true);
        add(bToDBtn, c);
        // Decimal to binary button
        c.dToBBtn();
        dToBBtn.addActionListener(this);
        dToBBtn.setOpaque(true);
        add(dToBBtn, c);
        // Input box
        c.inputBox();
        inputBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                convert();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                convert();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                convert();
            }
        });
        add(inputBox, c);
        // Output box
        c.outputBox();
        add(outputBox, c);
    }

    public void convert() {
        String inputTxt = inputBox.getText();
        try {
            if (bToDBtn.getBackground() == Tool.SELECTED) {
                outputBox.setText(String.valueOf(Integer.parseInt(inputTxt, 2)));
            } else if (dToBBtn.getBackground() == Tool.SELECTED) {
                outputBox.setText(Integer.toBinaryString(Integer.parseInt(inputTxt)));
            }
        } catch (Exception e) {
            outputBox.setText("Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dToBBtn.setBackground(e.getSource() == dToBBtn ? Tool.SELECTED : Tool.DESELECTED);
        bToDBtn.setBackground(e.getSource() == bToDBtn ? Tool.SELECTED : Tool.DESELECTED);
    }

    private static class Constraints extends GridBagConstraints {

        public void bToDBtn() {
            gridx = 1;
            gridy = 1;
            gridwidth = 1;
            fill = BOTH;
            anchor = LINE_START;
            weightx = 0.5;
            weighty = 0.5;
            insets = new Insets(7, 5, 0, 5);
        }

        public void dToBBtn() {
            gridx = 2;
            gridy = 1;
            gridwidth = 1;
            fill = BOTH;
            anchor = LINE_END;
            weightx = 0.5;
            weighty = 0.5;
            insets = new Insets(7, 0, 0, 5);
        }

        public void inputBox() {
            gridx = 1;
            gridy = 2;
            gridwidth = 2;
            fill = BOTH;
            anchor = PAGE_START;
            weightx = 1;
            weighty = 0.5;
            insets = new Insets(5, 5, 0, 5);
        }

        public void outputBox() {
            gridx = 1;
            gridy = 3;
            gridwidth = 2;
            fill = BOTH;
            anchor = PAGE_START;
            weightx = 1;
            weighty = 0.5;
            insets = new Insets(5, 5, 0, 5);
        }
    }
}
