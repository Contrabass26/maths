import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Menu extends JPanel implements ActionListener, ListSelectionListener {

    int ID;
    static boolean[] is_clearing = {false, false, false}; // For list box selection events
    JList<String> answers_list = new JList<>(Constants.answer_options); // Answers list box
    DefaultListModel<String> faves_model = new DefaultListModel<>();
    JList<String> faves_list = new JList<>(faves_model); // Favourites list box
    JList<String> learn_list = new JList<>(Constants.learn_options); // Learning list box
    JButton go_btn = new JButton("Go"); // Go button
    // Icons
    ImageIcon add_icon = new ImageIcon("add_icon.png");
    ImageIcon remove_icon = new ImageIcon("remove_icon.png");
    // Favourites buttons
    JButton add_faves_btn = new JButton(add_icon);
    JButton remove_faves_btn = new JButton(remove_icon);
    
    public Menu(int id) {
        // Set id
        ID = id;
        // Add favourites to list model
        for (int i = 0; i < Constants.faves.length; i++) faves_model.addElement(Constants.faves[i]);
        // Set layout
        setLayout(new GridBagLayout());
        MenuCns c = new MenuCns();
        // Answers label
        c.answers_lbl();
        JLabel answers_lbl = new JLabel("Calculators:");
        Font font = answers_lbl.getFont();
        Map<TextAttribute, Object> font_attributes = new HashMap<>(font.getAttributes());
        font_attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        answers_lbl.setFont(font.deriveFont(font_attributes));
        add(answers_lbl, c);
        // Favourites label
        c.faves_lbl();
        JLabel faves_lbl = new JLabel("Favourites:");
        faves_lbl.setFont(font.deriveFont(font_attributes));
        add(faves_lbl, c);
        // Add to favourites button
        c.add_faves_btn();
        add_faves_btn.addActionListener(this);
        add(add_faves_btn, c);
        // Remove from favourites button
        c.remove_faves_btn();
        remove_faves_btn.addActionListener(this);
        add(remove_faves_btn, c);
        // Learning label
        c.learn_lbl();
        JLabel learn_lbl = new JLabel("Learning:");
        learn_lbl.setFont(font.deriveFont(font_attributes));
        add(learn_lbl, c);
        // Answers list box
        c.answers_list();
        answers_list.addListSelectionListener(this);
        add(answers_list, c);
        // Favourites list box
        c.faves_list();
        faves_list.addListSelectionListener(this);
        add(faves_list, c);
        // Learning list box
        c.learn_list();
        learn_list.addListSelectionListener(this);
        add(learn_list, c);
        // Go button
        c.go_btn();
        go_btn.addActionListener(this);
        add(go_btn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == go_btn) {
            if (answers_list.getSelectedIndex() != -1) {
                Main.switch_panel(answers_list.getSelectedValue());
            } else if (faves_list.getSelectedIndex() != -1) {
                Main.switch_panel(faves_list.getSelectedValue());
            } else if (learn_list.getSelectedIndex() != -1) {
                Main.switch_panel(learn_list.getSelectedValue());
            }
        } else if (e.getSource() == add_faves_btn) {
            if (answers_list.getSelectedIndex() != -1) {
                Main.add_fave(answers_list.getSelectedValue());
                Main.write_faves();
                faves_model.addElement(answers_list.getSelectedValue());
            } else if (learn_list.getSelectedIndex() != -1) {
                Main.add_fave(learn_list.getSelectedValue());
                Main.write_faves();
                faves_model.addElement(learn_list.getSelectedValue());
            }
        } else if (e.getSource() == remove_faves_btn && faves_list.getSelectedIndex() != -1) {
            Main.remove_fave(faves_list.getSelectedIndex());
            faves_model.removeElementAt(faves_list.getSelectedIndex());
            Main.write_faves();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == answers_list) {
            if (!is_clearing[1] && !is_clearing[2]) {
                is_clearing[0] = true;
                learn_list.clearSelection();
                faves_list.clearSelection();
                is_clearing[0] = false;
            }
        } else if (e.getSource() == faves_list) {
            if (!is_clearing[0] && !is_clearing[2]) {
                is_clearing[1] = true;
                answers_list.clearSelection();
                learn_list.clearSelection();
                is_clearing[1] = false;
            }
        } else if (e.getSource() == learn_list) {
            if (!is_clearing[0] && !is_clearing[1]) {
                is_clearing[2] = true;
                answers_list.clearSelection();
                faves_list.clearSelection();
                is_clearing[2] = false;
            }
        }
    }
}
