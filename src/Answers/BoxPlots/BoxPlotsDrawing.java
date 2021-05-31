package Answers.BoxPlots;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoxPlotsDrawing extends JPanel {

    List<Integer> data_set = new ArrayList<>();

    public void paintComponent(Graphics g) {
        try {
            // CALCULATE SCALE
            // Get lowest power of 10 that accommodates data set range when multiplied by an integer from 1-9
            int zeros = String.valueOf(Collections.max(data_set)).length() - 1;
            int scale_base = (int) Math.pow(10, zeros);
            // Get lowest multiplier for scale base that accommodates data set range
            char multiplier_char = String.valueOf(Collections.max(data_set)).charAt(0);
            int multiplier = Integer.parseInt(String.valueOf(multiplier_char)) + 1;
            // Draw scale
            int height = (int) (getHeight() * 0.7); // Line height on panel
            g.drawLine(20, height, getWidth() - 20, height); // Draw main line
            int j = 0; // Counter for how many lines drawn
            for (int i = 20; i <= getWidth() - 20; i += (getWidth() - 40) / multiplier) {
                g.drawLine(i, height - 7, i, height + 7); // Notch on line
                String text = String.valueOf(j * scale_base); // Number to go under notch
                g.drawString(text, i - 4 * text.length(), height + 20); // Draw number on panel
                j++;
            }
            // CALCULATE AVERAGES
            // Median
            Collections.sort(data_set);
            double median = median(data_set);
            // Get index of median
            double median_index;
            if (data_set.size() % 2 == 0) {
                median_index = data_set.size() / 2.0 - 0.5;
            } else median_index = (data_set.size() + 1) / 2.0 - 1;
            // Lower quartile
            List<Integer> lower_list = data_set.subList(0, (int) Math.round(median_index + 0.1));
            double lq = median(lower_list);
            // Upper quartile
            List<Integer> upper_list = data_set.subList((int) Math.round(median_index + 0.1), data_set.size());
            double uq = median(upper_list);
            // Maximum and minimum values
            int max = Collections.max(data_set);
            int min = Collections.min(data_set);
            // DRAW BOX PLOT
            // "20 + one * num" converts num to an x coordinate on the panel
            height = getHeight() / 2; // Height of central line
            int one = (getWidth() - 40) / (scale_base * multiplier); // How many pixels 1 is on the panel
            // Start and end lines
            g.drawLine(20 + one * min, height - 7, 20 + one * min, height + 7);
            g.drawLine(20 + one * max, height - 7, 20 + one * max, height + 7);
            // Lines between extremes and quartiles
            g.drawLine(20 + one * min, height, (int) (20 + one * lq), height);
            g.drawLine((int) (20 + one * uq), height, 20 + one * max, height);
            // Quartiles
            g.drawLine((int) (20 + one * lq), height - 15, (int) (20 + one * lq), height + 15);
            g.drawLine((int) (20 + one * uq), height - 15, (int) (20 + one * uq), height + 15);
            // Median
            g.drawLine((int) (20 + one * median), height - 15, (int) (20 + one * median), height + 15);
            // Top and bottom lines
            g.drawLine((int) (20 + one * lq), height - 15, (int) (20 + one * uq), height - 15);
            g.drawLine((int) (20 + one * lq), height + 15, (int) (20 + one * uq), height + 15);
        } catch (Exception e) {
            // Do nothing
        }
    }
    
    public double median(List<Integer> list) {
        Collections.sort(list);
        double median;
        if (list.size() % 2 == 0) {
            int bottom = list.get(list.size() / 2 - 1);
            int top = list.get(list.size() / 2);
            median = (bottom + top) / 2.0;
        } else {
            median = list.get((list.size() + 1) / 2 - 1);
        }
        return median;
    }
}
