import Constraints.TrianglesCns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Triangles extends JPanel implements ActionListener {

    int ID;
    JTextField[] entries = new JTextField[6]; // Side and angle entries
    JButton back_btn = new JButton("<-- Back to menu"); // Back button
    JButton go_btn = new JButton("Calculate"); // Go button
    TrianglesDrawing draw_pnl = new TrianglesDrawing(); // Drawing panel

    public Triangles(int id) {
        super();
        // Set id
        ID = id;
        // Set layout
        setLayout(new GridBagLayout());
        JPanel top_pnl = new JPanel(new GridBagLayout());
        JPanel bottom_pnl = new JPanel(new GridLayout(1, 2));
        JPanel main_pnl = new JPanel(new GridBagLayout());
        TrianglesCns c = new TrianglesCns();
        // Title label
        c.title_lbl();
        JLabel title_lbl = new JLabel(Constants.IDENTIFIERS[ID]);
        title_lbl.setFont(title_lbl.getFont().deriveFont(32.0f));
        top_pnl.add(title_lbl, c);
        // Introduction label
        c.intro_lbl();
        JLabel intro_lbl = new JLabel(Constants.INTRO_TEXT[ID]);
        top_pnl.add(intro_lbl, c);
        // Labels and entries
        JLabel[] labels = new JLabel[6];
        String[] names = {"a", "b", "c", "A", "B", "C"};
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel(names[i] + ":");
            c.labels(i);
            main_pnl.add(labels[i], c);
            entries[i] = new JTextField();
            c.entries(i);
            main_pnl.add(entries[i], c);
        }
        // Go button
        c.go_btn();
        go_btn.addActionListener(this);
        add(go_btn, c);
        // Back button
        c.back_btn();
        back_btn.addActionListener(this);
        add(back_btn, c);
        // Finalise layout
        bottom_pnl.add(main_pnl);
        bottom_pnl.add(draw_pnl);
        c.top_pnl();
        add(top_pnl, c);
        c.bottom_pnl();
        add(bottom_pnl, c);
    }

    public void update() {
        // Convert text box contents to double, validate input, and set known values for drawing class
        int total = 0;
        boolean got_side = false;
        double[] values = new double[6];
        for (int i = 0; i < 6; i++) {
            // Convert input values to double
            if (!entries[i].getText().equals("")) values[i] = Double.parseDouble(entries[i].getText());
            // Set known values for drawing class
            draw_pnl.set(i, values[i]);
            // Input validation
            if (values[i] != 0) {
                total++;
                if (i < 3) got_side = true;
            }
        }
        // Complete validation
        String error_text = "";
        if (total != 3) {
            error_text += "Please provide 3 values; you have given " + total + ".";
        }
        if (!got_side) {
            if (!error_text.equals("")) error_text += " ";
            error_text += "Make sure at least one value is a side length.";
        }
        if (!error_text.equals("")) {
            JOptionPane.showMessageDialog(this, error_text, "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Do it three times so all values can be calculated
        for (int i = 0; i < 3; i++) {
            // SINE RULE
            // Input combinations that work, in the format: {a, A, b or B}
            int[][] sine_rule_combos = {{0, 3, 1}, {0, 3, 2}, {1, 4, 0}, {1, 4, 2}, {2, 5, 0}, {2, 5, 1}, {0, 3, 4}, {0, 3, 5}, {1, 4, 3}, {1, 4, 5}, {2, 5, 3}, {2, 5, 4}};
            // For each combination:
            combos_loop:
            for (int[] combo : sine_rule_combos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combos_loop;
                }
                // What are we calculating
                if (combo[2] < 3) {
                    System.out.println("Using sine rule to calculate " + (combo[2] + 3));
                    // Find an angle: asin(sin(A) / a * b)
                    double answer = Math.toDegrees(Math.asin(sin(values[combo[1]]) / values[combo[0]] * values[combo[2]]));
                    answer = Math.round(answer * 100) / 100.0;
                    draw_pnl.set(combo[2] + 3, answer);
                    entries[combo[2] + 3].setText(String.valueOf(answer));
                    values[combo[2] + 3] = answer;
                } else {
                    System.out.println("Using sine rule to calculate " + (combo[2] - 3));
                    // Find a side: a / sin(A) * sin(B)
                    double answer = values[combo[0]] / sin(values[combo[1]]) * sin(values[combo[2]]);
                    answer = Math.round(answer * 100) / 100.0;
                    draw_pnl.set(combo[2] - 3, answer);
                    entries[combo[2] - 3].setText(String.valueOf(answer));
                    values[combo[2] - 3] = answer;
                }
            }
            // COSINE RULE - Finding a
            // Input combinations that work, in the format: {b, c, A}
            int[][] cosine_rule_combos = {{0, 1, 5}, {0, 2, 4}, {1, 2, 3}};
            // For each combination:
            combos_loop:
            for (int[] combo : cosine_rule_combos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combos_loop;
                }
                System.out.println("Using cosine rule to calculate " + (combo[2] - 3));
                // sqrt(b^2 + c^2 - 2bc * cos(A))
                double b = values[combo[0]];
                double c = values[combo[1]];
                double A = values[combo[2]];
                double answer = Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2) - 2 * b * c * cos(A));
                answer = Math.round(answer * 100) / 100.0;
                draw_pnl.set(combo[2] - 3, answer);
                entries[combo[2] - 3].setText(String.valueOf(answer));
                values[combo[2] - 3] = answer;
            }
            // COSINE RULE - Finding A
            // Input combinations that work, in the format: {a, b, c}
            cosine_rule_combos = new int[][]{{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
            // For each combination
            combos_loop:
            for (int[] combo : cosine_rule_combos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combos_loop;
                }
                System.out.println("Using cosine rule to calculate " + (combo[0] + 3));
                // acos((b^2 + c^2 - a^2) / 2bc)
                double a = values[combo[0]];
                double b = values[combo[1]];
                double c = values[combo[2]];
                double answer = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c)));
                answer = Math.round(answer * 100) / 100.0;
                draw_pnl.set(combo[0] + 3, answer);
                entries[combo[0] + 3].setText(String.valueOf(answer));
                values[combo[0] + 3] = answer;
            }
        }
        // Redraw the diagram (not currently working)
        draw_pnl.repaint();
    }

    public static double sin(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    public static double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_btn) {
            for (int i = 0; i < 6; i++) {
                entries[i].setText("");
            }
            Main.switch_panel(Constants.IDENTIFIERS[0]);
        } else if (e.getSource() == go_btn) {
            update();
        }
    }
}
