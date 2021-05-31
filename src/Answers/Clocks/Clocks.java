package Answers.Clocks;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Clocks extends JPanel implements DocumentListener {

    int ID;
    JTextField input_box = new JTextField(); // Input box
    JLabel output_box = new JLabel("00:00"); // Output label

    public Clocks(int id) {
        super();
        // Set id
        ID = id;
        // Set layout
        setLayout(new GridBagLayout());
        ClocksCns c = new ClocksCns();
        // Input box
        c.input_box();
        input_box.getDocument().addDocumentListener(this);
        add(input_box, c);
        // Output box
        c.output_box();
        add(output_box, c);
    }

    public void convert() {
        try {
            // Get time
            int[] time = new int[2];
            time[0] = Integer.parseInt(input_box.getText().substring(0, input_box.getText().indexOf(':')));
            time[1] = Integer.parseInt(input_box.getText().substring(input_box.getText().indexOf(':') + 1, input_box.getText().indexOf(':') + 3));
            if (input_box.getText().endsWith("am")) {
                output_box.setText(time_str(time, ""));
            } else if (input_box.getText().endsWith("pm")) {
                time[0] += 12;
                output_box.setText(time_str(time, ""));
            } else {
                String suffix = "";
                if (time[0] < 12) suffix = "am";
                else if (time[0] > 12) {
                    suffix = "pm";
                    time[0] -= 12;
                }
                output_box.setText(time_str(time, suffix));
            }
        } catch (Exception e) {
            output_box.setText("Error");
        }
    }

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

    public String time_str(int[] time, String suffix) {
        // Hour
        String hour = String.valueOf(time[0]);
        if (hour.length() == 1) hour = "0" + hour;
        // Minute
        String minute = String.valueOf(time[1]);
        if (minute.length() == 1) minute = "0" + minute;
        // Return
        return hour + ":" + minute + suffix;
    }
}
