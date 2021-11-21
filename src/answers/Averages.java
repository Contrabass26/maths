package answers;

import main.Tool;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.DoubleStream;

public class Averages extends JPanel {

    private final JTextField inputBox = new JTextField(); // Input box
    private final JLabel outputBox = new JLabel("Error"); // Output box
    private final JButton[] typeBtn = new JButton[4]; // Average selector buttons
    private int selectedAverage = 0; // Selected average type

    public Averages() {
        super();
        // Set layout
        setLayout(new GridBagLayout());
        Constraints c = new Constraints();
        // Input box
        c.inputBox();
        inputBox.getDocument().addDocumentListener(new DocumentListener() {
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
        add(inputBox, c);
        // ActionListener for selector buttons
        ActionListener actionListener = e -> {
            for (int i = 0; i < typeBtn.length; i++) {
                if (typeBtn[i] == e.getSource()) {
                    typeBtn[i].setBackground(Tool.SELECTED);
                    selectedAverage = i;
                    update();
                } else typeBtn[i].setBackground(Tool.DESELECTED);
            }
        };
        // Average selector buttons
        String[] averageTypes = {"Mean", "Median", "Mode", "Range"};
        for (int i = 0; i < averageTypes.length; i++) {
            typeBtn[i] = new JButton(averageTypes[i]);
            c.typeBtn(i + 1);
            typeBtn[i].setOpaque(true);
            typeBtn[i].addActionListener(actionListener);
            add(typeBtn[i], c);
        }
        typeBtn[0].setBackground(Tool.SELECTED);
        // Output box
        c.outputBox();
        add(outputBox, c);
    }

    public void update() {
        try {
            // Get items
            double[] items = new double[]{};
            StringBuilder currentItem = new StringBuilder();
            for (int i = 0; i < inputBox.getText().length(); i++) {
                if (inputBox.getText().charAt(i) == ',') {
                    // Add item
                    double[] tempItems = items;
                    items = new double[tempItems.length + 1];
                    System.arraycopy(tempItems, 0, items, 0, tempItems.length);
                    items[items.length - 1] = Integer.parseInt(currentItem.toString());
                    // Reset current item
                    currentItem = new StringBuilder();
                    // Skip over space
                    i++;
                // Add to current item
                } else currentItem.append(inputBox.getText().charAt(i));
            }
            // Add final item
            double[] tempItems = items;
            items = new double[tempItems.length + 1];
            System.arraycopy(tempItems, 0, items, 0, tempItems.length);
            items[items.length - 1] = Integer.parseInt(currentItem.toString());
            // Calculate answer
            double answer = 0;
            Arrays.sort(items);
            switch (selectedAverage) {
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
            outputBox.setText(String.valueOf(answer));
        } catch (NumberFormatException e) {
            outputBox.setText("Error");
        }
    }

    private static class Constraints extends GridBagConstraints {

        public void inputBox() {
            gridx = 1;
            gridy = 1;
            gridwidth = 4;
            fill = BOTH;
            anchor = PAGE_START;
            weightx = 1;
            weighty = 1;
            insets = new Insets(5, 5, 0, 5);
        }

        public void typeBtn(int col) {
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

        public void outputBox() {
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
}
