import Constraints.AveragesCns;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.DoubleStream;

public class Averages extends JPanel implements ActionListener, DocumentListener {

    int ID;
    JTextField input_box = new JTextField(); // Input box
    JLabel output_box = new JLabel("Error"); // Output box
    JButton back_btn = new JButton("<-- Back to menu"); // Back button
    JButton[] type_btn = new JButton[4]; // Average selector buttons
    int selected_average = 0;

    public Averages(int id) {
        super();
        // Set id
        ID = id;
        // Set layout
        setLayout(new GridBagLayout());
        AveragesCns c = new AveragesCns();
        // Title label
        c.title_lbl();
        JLabel title_lbl = new JLabel(Constants.IDENTIFIERS[ID]);
        title_lbl.setFont(title_lbl.getFont().deriveFont(32.0f));
        add(title_lbl, c);
        // Introduction label
        c.intro_lbl();
        JLabel intro_lbl = new JLabel(Constants.INTRO_TEXT[ID]);
        add(intro_lbl, c);
        // Input box
        c.input_box();
        input_box.getDocument().addDocumentListener(this);
        add(input_box, c);
        // Average selector buttons
        String[] average_types = {"Mean", "Median", "Mode", "Range"};
        for (int i = 0; i < average_types.length; i++) {
            type_btn[i] = new JButton(average_types[i]);
            c.type_btn(i + 1);
            type_btn[i].setOpaque(true);
            type_btn[i].addActionListener(this);
            add(type_btn[i], c);
        }
        type_btn[0].setBackground(Constants.SELECTED);
        // Output box
        c.output_box();
        add(output_box, c);
        // Back button
        c.back_btn();
        back_btn.addActionListener(this);
        add(back_btn, c);
    }

    public void update() {
        try {
            // Get items
            double[] items = new double[]{};
            StringBuilder current_item = new StringBuilder();
            for (int i = 0; i < input_box.getText().length(); i++) {
                if (input_box.getText().charAt(i) == ',') {
                    // Add item
                    double[] temp_items = items;
                    items = new double[temp_items.length + 1];
                    System.arraycopy(temp_items, 0, items, 0, temp_items.length);
                    items[items.length - 1] = Integer.parseInt(current_item.toString());
                    // Reset current item
                    current_item = new StringBuilder();
                    // Skip over space
                    i++;
                // Add to current item
                } else current_item.append(input_box.getText().charAt(i));
            }
            // Add final item
            double[] temp_items = items;
            items = new double[temp_items.length + 1];
            System.arraycopy(temp_items, 0, items, 0, temp_items.length);
            items[items.length - 1] = Integer.parseInt(current_item.toString());
            // Calculate answer
            double answer = 0;
            Arrays.sort(items);
            switch (selected_average) {
                case 0 -> answer = DoubleStream.of(items).sum() / items.length;
                case 1 -> {
                    if (items.length % 2 == 0) {
                        answer = (items[items.length / 2 - 1] + items[items.length / 2]) / 2;
                    } else answer = items[(int) Math.floor(items.length / 2.0)];
                }
                case 2 -> {
                    HashMap<Double, Integer> counts = new HashMap<>();
                    for (double item : items) {
                        if (counts.containsKey(item)) {
                            counts.put(item, counts.get(item) + 1);
                        } else {
                            counts.put(item, 1);
                        }
                    }
                    int[] max = {0, 0};
                    for (int i = 0; i < items.length; i++) {
                        if (counts.get(items[i]) > max[1]) max = new int[]{i, counts.get(items[i])};
                    }
                    answer = items[max[0]];
                }
                case 3 -> {
                    double max = Double.MIN_VALUE;
                    double min = Double.MAX_VALUE;
                    for (double item : items) {
                        if (item < min) min = item;
                        if (item > max) max = item;
                    }
                    answer = max - min;
                }
            }
            output_box.setText(String.valueOf(answer));
        } catch (NumberFormatException e) {
            output_box.setText("Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_btn) {
            Main.switch_panel(Constants.IDENTIFIERS[0]);
        } else {
            for (int i = 0; i < type_btn.length; i++) {
                if (type_btn[i] == e.getSource()) {
                    type_btn[i].setBackground(Constants.SELECTED);
                    selected_average = i;
                    update();
                } else type_btn[i].setBackground(Constants.DESELECTED);
            }
        }
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
