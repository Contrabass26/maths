import javax.swing.*;
import java.awt.*;

public class TrianglesDrawing extends JPanel {

    double A, B, C, a, b, c;
    double[] a_coords, b_coords, c_coords;
    double sf;
    int[] padding = {40, 40};

    public void paint(Graphics g) {
        for (int i = 0; i < 6; i++) {
            if (get(i) == 0) return;
        }
        // Point alpha
        a_coords = new double[2];
        if (C > 90) a_coords[0] = 0 - cos(180 - C) * b;
        if (C < 90) a_coords[0] = cos(180 - C) * b;
        if (C < 90) a_coords[1] = sin(C) * b;
        if (B < 90) a_coords[1] = sin(B) * c;
        // Point beta
        b_coords = new double[]{a, 0};
        // Point gamma
        c_coords = new double[]{0, 0};
        // Make sure none are negative
        // x
        double x_lowest = Math.min(a_coords[0], Math.min(b_coords[0], c_coords[0]));
        if (x_lowest < 0) {
            a_coords[0] -= x_lowest;
            b_coords[0] -= x_lowest;
            c_coords[0] -= x_lowest;
        }
        // y
        double y_lowest = Math.min(a_coords[1], Math.min(b_coords[1], c_coords[1]));
        if (y_lowest < 0) {
            a_coords[1] -= y_lowest;
            b_coords[1] -= y_lowest;
            c_coords[1] -= y_lowest;
        }
        // Calculate available space
        double[] available = {getWidth() - padding[0] * 2, getHeight() - padding[1] * 2};
        // Get scale factor
        double[] sfs = new double[2];
        double x_highest = Math.max(a_coords[0], Math.max(b_coords[0], c_coords[0]));
        sfs[0] = x_highest / available[0];
        double y_highest = Math.max(a_coords[1], Math.max(b_coords[1], c_coords[1]));
        sfs[1] = y_highest / available[1];
        sf = Math.max(sfs[0], sfs[1]);
        // Scale coordinates
        a_coords = scale(a_coords);
        b_coords = scale(b_coords);
        c_coords = scale(c_coords);
        // Draw lines
        g.drawLine((int) a_coords[0], (int) a_coords[1], (int) b_coords[0], (int) b_coords[1]);
        g.drawLine((int) a_coords[0], (int) a_coords[1], (int) c_coords[0], (int) c_coords[1]);
        g.drawLine((int) b_coords[0], (int) b_coords[1], (int) c_coords[0], (int) c_coords[1]);
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
    
    public void set(int index, double new_value) {
        switch (index) {
            case 0 -> a = new_value;
            case 1 -> b = new_value;
            case 2 -> c = new_value;
            case 3 -> A = new_value;
            case 4 -> B = new_value;
            case 5 -> C = new_value;
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
