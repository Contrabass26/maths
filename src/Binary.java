import Constraints.BinaryCns;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Binary extends JPanel implements ActionListener, DocumentListener {

    int ID;
    JButton back_btn = new JButton("<-- Back to menu"); // Back button
    JTextField input_box = new JTextField(); // Input box
    JLabel output_box = new JLabel("0", SwingConstants.CENTER); // Output box
    // Mode switching buttons
    JButton b_to_d_btn = new JButton("Binary to decimal");
    JButton d_to_b_btn = new JButton("Decimal to binary");

    public Binary(int id) {
        super();
        // Set id
        ID = id;
        // Set layout
        setLayout(new GridBagLayout());
        BinaryCns c = new BinaryCns();
        // Title label
        c.title_lbl();
        JLabel title_lbl = new JLabel(Constants.IDENTIFIERS[ID]);
        title_lbl.setFont(title_lbl.getFont().deriveFont(32.0f));
        add(title_lbl, c);
        // Introduction label
        c.intro_lbl();
        JLabel intro_lbl = new JLabel(Constants.INTRO_TEXT[ID]);
        add(intro_lbl, c);
        // Binary to decimal button
        c.b_to_d_btn();
        b_to_d_btn.addActionListener(this);
        b_to_d_btn.setBackground(Constants.SELECTED);
        b_to_d_btn.setOpaque(true);
        add(b_to_d_btn, c);
        // Decimal to binary button
        c.d_to_b_btn();
        d_to_b_btn.addActionListener(this);
        d_to_b_btn.setOpaque(true);
        add(d_to_b_btn, c);
        // Input box
        c.input_box();
        input_box.getDocument().addDocumentListener(this);
        add(input_box, c);
        // Output box
        c.output_box();
        add(output_box, c);
        // Back button
        c.back_btn();
        back_btn.addActionListener(this);
        add(back_btn, c);
    }

    public void convert() {
        String input_txt = input_box.getText();
        try {
            if (b_to_d_btn.getBackground() == Constants.SELECTED) {
                output_box.setText(String.valueOf(Integer.parseInt(input_txt, 2)));
            } else if (d_to_b_btn.getBackground() == Constants.SELECTED) {
                output_box.setText(Integer.toBinaryString(Integer.parseInt(input_txt)));
            }
        } catch (Exception e) {
            output_box.setText("Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == d_to_b_btn) {
            d_to_b_btn.setBackground(Constants.SELECTED);
            b_to_d_btn.setBackground(Constants.DESELECTED);
        } else if (e.getSource() == b_to_d_btn) {
            b_to_d_btn.setBackground(Constants.SELECTED);
            d_to_b_btn.setBackground(Constants.DESELECTED);
        } else if (e.getSource() == back_btn) {
            input_box.setText("");
            Main.switch_panel(Constants.IDENTIFIERS[0]);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == input_box.getDocument()) {
            convert();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == input_box.getDocument()) {
            convert();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == input_box.getDocument()) {
            convert();
        }
    }
}
