import Constraints.AveragesCns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Averages extends JPanel implements ActionListener {

    int ID;
    JButton back_btn = new JButton("<-- Back to menu");

    public Averages(int id) {
        super();
        // Set id
        ID = id;
        // Set layout
        setLayout(new GridBagLayout());
        AveragesCns c = new AveragesCns();
        // Title label
        c.title_lbl();
        JLabel title_lbl = new JLabel(Constants.IDENTIFIERS[ID]);
        title_lbl.setFont(title_lbl.getFont().deriveFont(32.0f));
        add(title_lbl, c);
        // Introduction label
        c.intro_lbl();
        JLabel intro_lbl = new JLabel(Constants.INTRO_TEXT[ID]);
        add(intro_lbl, c);
        // Back button
        c.back_btn();
        back_btn.addActionListener(this);
        add(back_btn, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
