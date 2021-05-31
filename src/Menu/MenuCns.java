package Menu;

import java.awt.*;

public class MenuCns extends GridBagConstraints {

    public void answers_lbl() {
        gridx = 1;
        gridy = 1;
        gridwidth = 1;
        fill = HORIZONTAL;
        weightx = 0.5;
        weighty = 0;
        anchor = PAGE_END;
        insets = new Insets(10, 5, 4, 4);
    }

    public void faves_lbl() {
        gridx = 2;
        gridy = 1;
        gridwidth = 1;
        fill = HORIZONTAL;
        weightx = 0.5;
        weighty = 0;
        anchor = PAGE_END;
        insets = new Insets(10, 4, 4, 10);
    }

    public void add_faves_btn() {
        gridx = 3;
        gridy = 1;
        gridwidth = 1;
        fill = NONE;
        weightx = 0;
        weighty = 0;
        anchor = LINE_END;
        insets = new Insets(10, 0, 0, 0);
    }

    public void remove_faves_btn() {
        gridx = 4;
        gridy = 1;
        gridwidth = 1;
        fill = NONE;
        weightx = 0;
        weighty = 0;
        anchor = LINE_END;
        insets = new Insets(10, 0, 0, 4);
    }

    public void questions_lbl() {
        gridx = 5;
        gridy = 1;
        gridwidth = 1;
        fill = HORIZONTAL;
        weightx = 0.5;
        weighty = 0;
        anchor = PAGE_END;
        insets = new Insets(10, 4, 4, 5);
    }

    public void answers_list() {
        gridx = 1;
        gridy = 2;
        gridwidth = 1;
        fill = BOTH;
        weightx = 1;
        weighty = 1;
        anchor = LINE_START;
        insets = new Insets(4, 5, 5, 4);
    }

    public void faves_list() {
        gridx = 2;
        gridy = 2;
        gridwidth = 3;
        fill = BOTH;
        weightx = 1;
        weighty = 1;
        anchor = CENTER;
        insets = new Insets(4, 4, 5, 4);
    }

    public void questions_list() {
        gridx = 5;
        gridy = 2;
        gridwidth = 1;
        fill = BOTH;
        weightx = 1;
        weighty = 1;
        anchor = LINE_END;
        insets = new Insets(4, 4, 5, 5);
    }

    public void go_btn() {
        gridx = 1;
        gridy = 3;
        gridwidth = 5;
        fill = BOTH;
        weightx = 0.5;
        weighty = 0.2;
        anchor = PAGE_END;
        insets = new Insets(5, 5, 5, 5);
    }
}
