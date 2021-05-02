package Constraints;

import java.awt.*;

public class BinaryCns extends GridBagConstraints {

    public void title_lbl() {
        gridx = 1;
        gridy = 1;
        gridwidth = 2;
        fill = HORIZONTAL;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 0;
        insets = new Insets(5, 5, 0, 5);
    }

    public void intro_lbl() {
        gridx = 1;
        gridy = 2;
        gridwidth = 2;
        fill = HORIZONTAL;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 0;
        insets = new Insets(5, 5, 0, 5);
    }

    public void b_to_d_btn() {
        gridx = 1;
        gridy = 3;
        gridwidth = 1;
        fill = BOTH;
        anchor = LINE_START;
        weightx = 0.5;
        weighty = 0.5;
        insets = new Insets(7, 5, 0, 5);
    }

    public void d_to_b_btn() {
        gridx = 2;
        gridy = 3;
        gridwidth = 1;
        fill = BOTH;
        anchor = LINE_END;
        weightx = 0.5;
        weighty = 0.5;
        insets = new Insets(7, 0, 0, 5);
    }

    public void input_box() {
        gridx = 1;
        gridy = 4;
        gridwidth = 2;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 0.5;
        insets = new Insets(5, 5, 0, 5);
    }

    public void output_box() {
        gridx = 1;
        gridy = 5;
        gridwidth = 2;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 0.5;
        insets = new Insets(5, 5, 0, 5);
    }

    public void back_btn() {
        gridx = 1;
        gridy = 6;
        gridwidth = 2;
        fill = BOTH;
        anchor = PAGE_END;
        weightx = 1;
        weighty = 0.3;
        insets = new Insets(5, 5, 5, 5);
    }
}
