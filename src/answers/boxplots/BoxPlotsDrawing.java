package answers.boxplots;

import utils.StatisticsUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoxPlotsDrawing extends JPanel {

    protected List<Integer> dataSet = new ArrayList<>();

    public void paint(Graphics g) {
        try {
            // CALCULATE SCALE
            // Get the lowest power of 10 that accommodates data set range when multiplied by an integer from 1-9
            int zeros = String.valueOf(Collections.max(dataSet)).length() - 1;
            int scaleBase = (int) Math.pow(10, zeros);
            // Get the lowest multiplier for scale base that accommodates data set range
            char multiplierChar = String.valueOf(Collections.max(dataSet)).charAt(0);
            int multiplier = Integer.parseInt(String.valueOf(multiplierChar)) + 1;
            // Draw scale
            int height = (int) (getHeight() * 0.7); // Line height on panel
            g.drawLine(20, height, getWidth() - 20, height); // Draw main line
            int j = 0; // Counter for how many lines drawn
            for (int i = 20; i <= getWidth() - 20; i += (getWidth() - 40) / multiplier) {
                g.drawLine(i, height - 7, i, height + 7); // Notch on line
                String text = String.valueOf(j * scaleBase); // Number to go under notch
                g.drawString(text, i - 4 * text.length(), height + 20); // Draw number on panel
                j++;
            }

            // CALCULATE AVERAGES
            // Median, lq and uq
            double median = StatisticsUtil.median(dataSet);
            double lq = StatisticsUtil.lowerQuartile(dataSet);
            double uq = StatisticsUtil.upperQuartile(dataSet);
            // Maximum and minimum values
            int max = Collections.max(dataSet);
            int min = Collections.min(dataSet);

            // DRAW BOX PLOT
            // "20 + one * num" converts num to an x coordinate on the panel
            height = getHeight() / 2; // Height of central line
            int one = (getWidth() - 40) / (scaleBase * multiplier); // How many pixels 1 is on the panel
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
}
