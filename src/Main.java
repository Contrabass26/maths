import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Main extends JFrame {

    // Panels
    static JPanel outer_pnl = new JPanel();
    static Menu menu_pnl = new Menu(0);
    static Averages averages_pnl = new Averages(1);
    static Binary binary_pnl = new Binary(2);
    static Clocks clocks_pnl = new Clocks(3);
    static Triangles triangles_pnl = new Triangles(4);

    public Main() {
        // Window properties
        super("Maths");
        setSize(Constants.WN_SIZE[0], Constants.WN_SIZE[1]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Identifiers
        Constants.IDENTIFIERS = Constants.update_ids();
        // Set up layout
        CardLayout cards = new CardLayout();
        outer_pnl.setLayout(cards);
        // Set up cards
        outer_pnl.add(menu_pnl, Constants.IDENTIFIERS[0]);
        outer_pnl.add(averages_pnl, Constants.IDENTIFIERS[1]);
        outer_pnl.add(binary_pnl, Constants.IDENTIFIERS[2]);
        outer_pnl.add(clocks_pnl, Constants.IDENTIFIERS[3]);
        outer_pnl.add(triangles_pnl, Constants.IDENTIFIERS[4]);
        // Bring menu panel to front
        cards.show(outer_pnl, Constants.IDENTIFIERS[0]);
        // Finalise setup
        add(outer_pnl);
        setVisible(true);
    }

    public static void switch_panel(String id) {
        CardLayout c = (CardLayout) (outer_pnl.getLayout());
        c.show(outer_pnl, id);
    }
    
    public static String[] read_faves() {
        String[] temp_faves = new String[0];
        try {
            FileReader file = new FileReader("faves.txt");
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                temp_faves = Arrays.copyOf(temp_faves, temp_faves.length + 1);
                temp_faves[temp_faves.length - 1] = line;
            }
            reader.close();
            return temp_faves;
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static void write_faves() {
        try {
            FileWriter file = new FileWriter("faves.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < Constants.faves.length; i++) {
                writer.write(Constants.faves[i]);
                if (i != Constants.faves.length - 1) writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            // Do nothing
        }
    }

    public static void add_fave(String fave) {
        Constants.faves = Arrays.copyOf(Constants.faves, Constants.faves.length + 1);
        Constants.faves[Constants.faves.length - 1] = fave;
    }

    public static void remove_fave(int index) {
        String[] first_half = Arrays.copyOfRange(Constants.faves, 0, index);
        String[] second_half = Arrays.copyOfRange(Constants.faves, index + 1, Constants.faves.length);
        Constants.faves = new String[first_half.length + second_half.length];
        System.arraycopy(first_half, 0, Constants.faves, 0, first_half.length);
        System.arraycopy(second_half, 0, Constants.faves, first_half.length, second_half.length);
    }
}
