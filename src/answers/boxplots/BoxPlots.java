package answers.boxplots;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class BoxPlots extends JPanel {

    private final JTextField dataInput = new JTextField(); // Data set text box
    private final BoxPlotsDrawing panel = new BoxPlotsDrawing(); // Drawing panel

    public BoxPlots() {
        super();
        setLayout(new GridLayout(2, 1));
        // Drawing panel
        add(panel);
        // Data set entry box
        dataInput.getDocument().addDocumentListener(new DocumentListener() {
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
        });
        add(dataInput);
    }

    public void update() {
        try {
            String[] dataSet = dataInput.getText().split(",");
            panel.dataSet = new ArrayList<>();
            for (String datum : dataSet) {
                panel.dataSet.add(Integer.parseInt(datum));
            }
        } catch (Exception e) {
            // Do nothing
        }
        // Repaint panel
        panel.repaint();
    }
}
