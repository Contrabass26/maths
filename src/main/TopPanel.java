package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {

    // Labels
    private final JLabel titleLbl = new JLabel("Title");
    private final JLabel introLbl = new JLabel("Subtitle");

    public TopPanel() {
        // Set up layout
        super();
        setLayout(new GridLayout(3, 1));
        // Back button
        JButton backBtn = new JButton("<-- Back to menu");
        backBtn.addActionListener(e -> Window.window.switchPanel(Tool.getTool(0)));
        add(backBtn);
        // Title and intro labels
        titleLbl.setFont(titleLbl.getFont().deriveFont(32.0f));
        add(titleLbl);
        add(introLbl);
    }

    public void setTitle(String text) {
        titleLbl.setText(text);
    }

    public void setIntro(String text) {
        introLbl.setText(text);
    }
}
