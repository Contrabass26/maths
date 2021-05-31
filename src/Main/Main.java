package Main;

import Answers.Averages.*;
import Answers.Binary.*;
import Answers.BoxPlots.*;
import Answers.Clocks.*;
import Answers.Triangles.*;
import Menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Main extends JFrame {

    // General panels
    static JPanel top_pnl = new TopPanel();
    static JPanel outer_pnl = new JPanel();
    static Menu menu_pnl = new Menu(0);
    // Answers panels
    static Averages averages_ans_pnl = new Averages(1);
    static Binary binary_ans_pnl = new Binary(2);
    static Clocks clocks_ans_pnl = new Clocks(3);
    static Triangles triangles_ans_pnl = new Triangles(4);
    static BoxPlots box_plots_ans_pnl = new BoxPlots(5);

    public Main() {
        // Window properties
        super("Maths");
        setSize(Constants.WN_SIZE[0], Constants.WN_SIZE[1]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Set up layout
        setLayout(new GridBagLayout());
        MainCns c = new MainCns();
        c.top_pnl();
        add(top_pnl, c);
        c.outer_pnl();
        add(outer_pnl, c);
        // Outer panel layout
        CardLayout cards = new CardLayout();
        outer_pnl.setLayout(cards);
        // Set up cards
        outer_pnl.add(menu_pnl, Constants.IDENTIFIERS[0]);
        outer_pnl.add(averages_ans_pnl, Constants.IDENTIFIERS[1]);
        outer_pnl.add(binary_ans_pnl, Constants.IDENTIFIERS[2]);
        outer_pnl.add(clocks_ans_pnl, Constants.IDENTIFIERS[3]);
        outer_pnl.add(triangles_ans_pnl, Constants.IDENTIFIERS[4]);
        outer_pnl.add(box_plots_ans_pnl, Constants.IDENTIFIERS[5]);
        // Bring menu panel to front
        switch_panel(0);
        // Finalise setup
        setVisible(true);
    }

    public static void switch_panel(int id) {
        CardLayout c = (CardLayout) outer_pnl.getLayout();
        c.show(outer_pnl, Constants.IDENTIFIERS[id]);
        TopPanel.set_title(Constants.IDENTIFIERS[id]);
        TopPanel.set_intro(Constants.INTRO_TEXT[id]);
    }

    public static void switch_panel(String id) {
        CardLayout c = (CardLayout) outer_pnl.getLayout();
        c.show(outer_pnl, id);
        TopPanel.set_title(id);
        TopPanel.set_intro(Constants.INTRO_TEXT_MAP.get(id));
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
