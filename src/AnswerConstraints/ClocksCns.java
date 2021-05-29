package AnswerConstraints;

import java.awt.*;

public class ClocksCns extends GridBagConstraints {

    public ClocksCns() {
        weightx = 1;
    }

    public void input_box() {
        gridy = 3;
        fill = BOTH;
        weighty = 0.5;
        insets = new Insets(5, 5, 0, 5);

    }

    public void output_box() {
        gridy = 4;
        fill = BOTH;
        weighty = 0.5;
        insets = new Insets(5, 5, 0, 5);
    }

}
