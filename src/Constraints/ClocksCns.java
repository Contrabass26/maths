package Constraints;

import java.awt.*;

public class ClocksCns extends GridBagConstraints {

    public ClocksCns() {
        weightx = 1;
    }

    public void title_lbl() {
        gridy = 1;
        fill = HORIZONTAL;
        weighty = 0;
        insets = new Insets(5, 5, 0, 5);
    }

    public void intro_lbl() {
        gridy = 2;
        fill = HORIZONTAL;
        weighty = 0;
        insets = new Insets(5, 5, 0, 5);
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

    public void back_btn() {
        gridy = 5;
        fill = BOTH;
        weighty = 0.5;
        insets = new Insets(5, 5, 5, 5);
    }
}
