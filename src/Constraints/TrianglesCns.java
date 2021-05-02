package Constraints;

import java.awt.*;

public class TrianglesCns extends GridBagConstraints {

    public void top_pnl() {
        gridx = 1;
        gridy = 1;
        gridwidth = 1;
        fill = BOTH;
        weighty = 0.3;
        weightx = 1;
        insets = new Insets(0, 0, 0, 0);
    }

    public void bottom_pnl() {
        gridx = 1;
        gridy = 2;
        gridwidth = 1;
        fill = BOTH;
        weighty = 0.7;
        weightx = 1;
        insets = new Insets(0, 0, 0, 0);
    }

    public void title_lbl() {
        gridx = 1;
        gridy = 1;
        gridwidth = 1;
        fill = HORIZONTAL;
        weightx = 1;
        weighty = 1;
        insets = new Insets(0, 5, 0, 0);
    }

    public void intro_lbl() {
        gridx = 1;
        gridy = 2;
        gridwidth = 2;
        fill = HORIZONTAL;
        weightx = 1;
        weighty = 1;
        insets = new Insets(0, 5, 0, 0);
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

    public void go_btn() {
        gridx = 1;
        gridy = 7;
        gridwidth = 1;
        fill = BOTH;
        weightx = 0.5;
        weighty = 0.5;
        insets = new Insets(5, 5, 5, 5);
    }

    public void back_btn() {
        gridx = 2;
        gridy = 7;
        gridwidth = 1;
        fill = BOTH;
        weightx = 0.5;
        weighty = 0.5;
        insets = new Insets(5, 0, 5, 5);
    }
}
