package answers;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// TODO: Use com.contrabass.Time instead
public class Clocks extends JPanel {

    private final JTextField inputBox = new JTextField(); // Input box
    private final JLabel outputBox = new JLabel("00:00"); // Output label

    public Clocks() {
        super();
        // Set layout
        setLayout(new GridBagLayout());
        Constraints c = new Constraints();
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
        try {
            // Get time
            int[] time = new int[2];
            time[0] = Integer.parseInt(inputBox.getText().substring(0, inputBox.getText().indexOf(':')));
            time[1] = Integer.parseInt(inputBox.getText().substring(inputBox.getText().indexOf(':') + 1, inputBox.getText().indexOf(':') + 3));
            if (inputBox.getText().endsWith("am")) {
                outputBox.setText(timeStr(time, ""));
            } else if (inputBox.getText().endsWith("pm")) {
                time[0] += 12;
                outputBox.setText(timeStr(time, ""));
            } else {
                String suffix = "";
                if (time[0] < 12) suffix = "am";
                else if (time[0] > 12) {
                    suffix = "pm";
                    time[0] -= 12;
                }
                outputBox.setText(timeStr(time, suffix));
            }
        } catch (Exception e) {
            outputBox.setText("Error");
        }
    }

    private static String timeStr(int[] time, String suffix) {
        // Hour
        String hour = String.valueOf(time[0]);
        if (hour.length() == 1) hour = "0" + hour;
        // Minute
        String minute = String.valueOf(time[1]);
        if (minute.length() == 1) minute = "0" + minute;
        // Return
        return hour + ":" + minute + suffix;
    }

    private static class Constraints extends GridBagConstraints {

        public Constraints() {
            weightx = 1;
        }

        public void inputBox() {
            gridy = 3;
            fill = BOTH;
            weighty = 0.5;
            insets = new Insets(5, 5, 0, 5);

        }

        public void outputBox() {
            gridy = 4;
            fill = BOTH;
            weighty = 0.5;
            insets = new Insets(5, 5, 0, 5);
        }

    }
}
