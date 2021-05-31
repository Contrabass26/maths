package Answers.Averages;

import java.awt.*;

public class AveragesCns extends GridBagConstraints {

    public void input_box() {
        gridx = 1;
        gridy = 1;
        gridwidth = 4;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 1;
        insets = new Insets(5, 5, 0, 5);
    }

    public void type_btn(int col) {
        gridx = col;
        gridy = 2;
        gridwidth = 1;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 1;
        if (col == 4) insets = new Insets(5, 5, 0, 5);
        else insets = new Insets(5, 5, 0, 0);
    }

    public void output_box() {
        gridx = 1;
        gridy = 3;
        gridwidth = 4;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 1;
        insets = new Insets(5, 5, 0, 5);
    }

}
