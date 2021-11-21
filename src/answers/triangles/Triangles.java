package answers.triangles;

import javax.swing.*;
import java.awt.*;

public class Triangles extends JPanel {

    private final JTextField[] entries = new JTextField[6]; // Side and angle entries
    private final JButton goBtn = new JButton("Calculate"); // Go button
    private final TrianglesDrawing panel = new TrianglesDrawing(); // Drawing panel

    public Triangles() {
        super();
        // Set layout
        setLayout(new GridBagLayout());
        JPanel topPnl = new JPanel(new GridBagLayout());
        JPanel bottomPnl = new JPanel(new GridLayout(1, 2));
        JPanel mainPnl = new JPanel(new GridBagLayout());
        Constraints c = new Constraints();
        // Labels and entries
        JLabel[] labels = new JLabel[6];
        String[] names = {"a", "b", "c", "A", "B", "C"};
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel(names[i] + ":");
            c.labels(i);
            mainPnl.add(labels[i], c);
            entries[i] = new JTextField();
            c.entries(i);
            mainPnl.add(entries[i], c);
        }
        // Go button
        c.goBtn();
        goBtn.addActionListener(e -> {
            if (e.getSource() == goBtn) {
                update();
            }
        });
        add(goBtn, c);
        // Finalise layout
        bottomPnl.add(mainPnl);
        bottomPnl.add(panel);
        c.topPnl();
        add(topPnl, c);
        c.bottomPnl();
        add(bottomPnl, c);
    }

    public void update() {
        // Convert text box contents to double, validate input, and set known values for drawing class
        int total = 0;
        boolean gotSide = false;
        double[] values = new double[6];
        for (int i = 0; i < 6; i++) {
            // Convert input values to double
            if (!entries[i].getText().equals("")) values[i] = Double.parseDouble(entries[i].getText());
            // Set known values for drawing class
            panel.set(i, values[i]);
            // Input validation
            if (values[i] != 0) {
                total++;
                if (i < 3) gotSide = true;
            }
        }
        // Complete validation
        String errorText = "";
        if (total != 3) {
            errorText += "Please provide 3 values; you have given " + total + ".";
        }
        if (!gotSide) {
            if (!errorText.equals("")) errorText += " ";
            errorText += "Make sure at least one value is a side length.";
        }
        if (!errorText.equals("")) {
            JOptionPane.showMessageDialog(this, errorText, "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Do it three times so all values can be calculated
        for (int i = 0; i < 3; i++) {
            // SINE RULE
            // Input combinations that work, in the format: {a, A, b or B}
            int[][] sineRuleCombos = {{0, 3, 1}, {0, 3, 2}, {1, 4, 0}, {1, 4, 2}, {2, 5, 0}, {2, 5, 1}, {0, 3, 4}, {0, 3, 5}, {1, 4, 3}, {1, 4, 5}, {2, 5, 3}, {2, 5, 4}};
            // For each combination:
            combosLoop:
            for (int[] combo : sineRuleCombos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combosLoop;
                }
                // What are we calculating
                if (combo[2] < 3) {
                    System.out.println("Using sine rule to calculate " + (combo[2] + 3));
                    // Find an angle: asin(sin(A) / a * b)
                    double answer = Math.toDegrees(Math.asin(sin(values[combo[1]]) / values[combo[0]] * values[combo[2]]));
                    answer = Math.round(answer * 100) / 100.0;
                    panel.set(combo[2] + 3, answer);
                    entries[combo[2] + 3].setText(String.valueOf(answer));
                    values[combo[2] + 3] = answer;
                } else {
                    System.out.println("Using sine rule to calculate " + (combo[2] - 3));
                    // Find a side: a / sin(A) * sin(B)
                    double answer = values[combo[0]] / sin(values[combo[1]]) * sin(values[combo[2]]);
                    answer = Math.round(answer * 100) / 100.0;
                    panel.set(combo[2] - 3, answer);
                    entries[combo[2] - 3].setText(String.valueOf(answer));
                    values[combo[2] - 3] = answer;
                }
            }
            // COSINE RULE - Finding "a"
            // Input combinations that work, in the format: {b, c, A}
            int[][] cosineRuleCombos = {{0, 1, 5}, {0, 2, 4}, {1, 2, 3}};
            // For each combination:
            combosLoop:
            for (int[] combo : cosineRuleCombos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combosLoop;
                }
                System.out.println("Using cosine rule to calculate " + (combo[2] - 3));
                // sqrt(b^2 + c^2 - 2bc * cos(A))
                double b = values[combo[0]];
                double c = values[combo[1]];
                double A = values[combo[2]];
                double answer = Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2) - 2 * b * c * cos(A));
                answer = Math.round(answer * 100) / 100.0;
                panel.set(combo[2] - 3, answer);
                entries[combo[2] - 3].setText(String.valueOf(answer));
                values[combo[2] - 3] = answer;
            }
            // COSINE RULE - Finding "A"
            // Input combinations that work, in the format: {a, b, c}
            cosineRuleCombos = new int[][]{{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
            // For each combination
            combosLoop:
            for (int[] combo : cosineRuleCombos) {
                // Check if it applies
                for (int j = 0; j < 3; j++) {
                    if (values[combo[j]] == 0) continue combosLoop;
                }
                System.out.println("Using cosine rule to calculate " + (combo[0] + 3));
                // acos((b^2 + c^2 - a^2) / 2bc)
                double a = values[combo[0]];
                double b = values[combo[1]];
                double c = values[combo[2]];
                double answer = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c)));
                answer = Math.round(answer * 100) / 100.0;
                panel.set(combo[0] + 3, answer);
                entries[combo[0] + 3].setText(String.valueOf(answer));
                values[combo[0] + 3] = answer;
            }
        }
        // Redraw the diagram (not currently working)
        panel.repaint();
    }

    public static double sin(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    public static double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }

    private static class Constraints extends GridBagConstraints {

        public void topPnl() {
            gridx = 1;
            gridy = 1;
            gridwidth = 1;
            fill = BOTH;
            weighty = 0.3;
            weightx = 1;
            insets = new Insets(0, 0, 0, 0);
        }

        public void bottomPnl() {
            gridx = 1;
            gridy = 2;
            gridwidth = 1;
            fill = BOTH;
            weighty = 0.7;
            weightx = 1;
            insets = new Insets(0, 0, 0, 0);
        }

        public void labels(int index) {
            gridx = 1;
            gridy = index;
            gridwidth = 1;
            fill = NONE;
            weightx = 0;
            weighty = 0.5;
            insets = new Insets(0, 5, 0, 5);
        }

        public void entries(int index) {
            gridx = 2;
            gridy = index;
            gridwidth = 1;
            fill = HORIZONTAL;
            weightx = 1;
            weighty = 0.5;
            insets = new Insets(0, 0, 0, 0);
        }

        public void goBtn() {
            gridx = 1;
            gridy = 7;
            gridwidth = 1;
            fill = BOTH;
            weightx = 0.5;
            weighty = 0.5;
            insets = new Insets(5, 5, 5, 5);
        }

    }
}
