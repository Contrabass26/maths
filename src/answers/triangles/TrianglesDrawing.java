package answers.triangles;

import javax.swing.*;
import java.awt.*;

public class TrianglesDrawing extends JPanel {

    private double A, B, C, a, b, c;
    private double sf;
    private final int[] padding = {40, 40};

    public void paint(Graphics g) {
        for (int i = 0; i < 6; i++) {
            if (get(i) == 0) return;
        }
        // Point alpha
        double[] aCoords = new double[2];
        if (C > 90) aCoords[0] = 0 - cos(180 - C) * b;
        if (C < 90) aCoords[0] = cos(180 - C) * b;
        if (C < 90) aCoords[1] = sin(C) * b;
        if (B < 90) aCoords[1] = sin(B) * c;
        // Point beta
        double[] bCoords = new double[]{a, 0};
        // Point gamma
        double[] cCoords = new double[]{0, 0};
        // Make sure none are negative
        // x
        double xLowest = Math.min(aCoords[0], Math.min(bCoords[0], cCoords[0]));
        if (xLowest < 0) {
            aCoords[0] -= xLowest;
            bCoords[0] -= xLowest;
            cCoords[0] -= xLowest;
        }
        // y
        double yLowest = Math.min(aCoords[1], Math.min(bCoords[1], cCoords[1]));
        if (yLowest < 0) {
            aCoords[1] -= yLowest;
            bCoords[1] -= yLowest;
            cCoords[1] -= yLowest;
        }
        // Calculate available space
        double[] available = {getWidth() - padding[0] * 2, getHeight() - padding[1] * 2};
        // Get scale factor
        double[] sfs = new double[2];
        double xHighest = Math.max(aCoords[0], Math.max(bCoords[0], cCoords[0]));
        sfs[0] = xHighest / available[0];
        double yHighest = Math.max(aCoords[1], Math.max(bCoords[1], cCoords[1]));
        sfs[1] = yHighest / available[1];
        sf = Math.max(sfs[0], sfs[1]);
        // Scale coordinates
        aCoords = scale(aCoords);
        bCoords = scale(bCoords);
        cCoords = scale(cCoords);
        // Draw lines
        g.drawLine((int) aCoords[0], (int) aCoords[1], (int) bCoords[0], (int) bCoords[1]);
        g.drawLine((int) aCoords[0], (int) aCoords[1], (int) cCoords[0], (int) cCoords[1]);
        g.drawLine((int) bCoords[0], (int) bCoords[1], (int) cCoords[0], (int) cCoords[1]);
    }

    public double[] scale(double[] coords) {
        coords[0] = coords[0] / sf + padding[0];
        coords[1] = coords[1] / sf + padding[1];
        return coords;
    }

    public static double sin(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    public static double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }
    
    public void set(int index, double newValue) {
        switch (index) {
            case 0 -> a = newValue;
            case 1 -> b = newValue;
            case 2 -> c = newValue;
            case 3 -> A = newValue;
            case 4 -> B = newValue;
            case 5 -> C = newValue;
        }
    }
    
    public double get(int index) {
        switch (index) {
            case 0: { return a; }
            case 1: { return b; }
            case 2: { return c; }
            case 3: { return A; }
            case 4: { return B; }
            case 5: { return C; }
            default: return 0;
        }
    }
}
