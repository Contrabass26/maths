package Answers.BoxPlots;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class BoxPlots extends JPanel implements DocumentListener {

    int ID;
    JTextField data_input = new JTextField(); // Data set text box
    BoxPlotsDrawing draw_pnl = new BoxPlotsDrawing(); // Drawing panel

    public BoxPlots(int id) {
        super();
        ID = id;
        // Set layout
        setLayout(new GridLayout(2, 1));
        // Drawing panel
        add(draw_pnl);
        // Data set entry box
        data_input.getDocument().addDocumentListener(this);
        add(data_input);
    }

    public void update() {
        try {
            String[] data_set = data_input.getText().split(",");
            draw_pnl.data_set = new ArrayList<>();
            for (String datum : data_set) {
                draw_pnl.data_set.add(Integer.parseInt(datum));
            }
        } catch (Exception e) {
            // Do nothing
        }
        // Repaint panel
        draw_pnl.repaint();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }
}
