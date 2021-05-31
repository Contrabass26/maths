package Main;

import java.awt.*;

public class MainCns extends GridBagConstraints {

    public void top_pnl() {
        gridy = 1;
        fill = HORIZONTAL;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 0;
    }

    public void outer_pnl() {
        gridy = 2;
        fill = BOTH;
        anchor = PAGE_START;
        weightx = 1;
        weighty = 1;
    }
}
