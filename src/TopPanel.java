import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel implements ActionListener {

    // Back button
    JButton back_btn = new JButton("<-- Back to menu");
    // Labels
    static JLabel title_lbl = new JLabel("Title");
    static JLabel intro_lbl = new JLabel("Subtitle");

    public TopPanel() {
        // Set up layout
        super();
        setLayout(new GridLayout(3, 1));
        // Back button
        back_btn.addActionListener(this);
        add(back_btn);
        // Title and intro labels
        title_lbl.setFont(title_lbl.getFont().deriveFont(32.0f));
        add(title_lbl);
        add(intro_lbl);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_btn) {
            Main.switch_panel(0);
        }
    }

    public static void set_title(String text) {
        title_lbl.setText(text);
    }

    public static void set_intro(String text) {
        intro_lbl.setText(text);
    }
}
