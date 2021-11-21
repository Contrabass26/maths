package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class Window extends JFrame {

    public static final String TITLE = "Maths";
    public static final int[] WN_SIZE = {700, 500};

    public static Window window = null;

    private final TopPanel topPanel = new TopPanel();
    private final JPanel outerPnl = new JPanel(new CardLayout());

    public Window() {
        // Window properties
        super(TITLE);
        setSize(WN_SIZE[0], WN_SIZE[1]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Set up layout
        setLayout(new GridBagLayout());
        Constraints c = new Constraints();
        c.topPnl();
        // General panels
        add(topPanel, c);
        c.outerPnl();
        add(outerPnl, c);
        // Add tools
        Tool.initTools();
        for (Tool tool : Tool.tools) {
            outerPnl.add(tool.id, tool.panel);
        }
        // Reload menu panel tools
        ((Menu) Tool.getTool(0).panel).reloadTools();
        // Bring menu panel to front
        switchPanel(Tool.getTool(0));
        // Finalise setup
        setVisible(true);
    }

    public void switchPanel(Tool tool) {
        CardLayout c = (CardLayout) outerPnl.getLayout();
        c.show(outerPnl, tool.id);
        topPanel.setTitle(tool.title);
        topPanel.setIntro(tool.intro);
    }
    
    public static String[] readFaves() {
        String[] tempFaves = new String[0];
        try {
            FileReader file = new FileReader("faves.txt");
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                tempFaves = Arrays.copyOf(tempFaves, tempFaves.length + 1);
                tempFaves[tempFaves.length - 1] = line;
            }
            reader.close();
            return tempFaves;
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static void writeFaves() {
        try {
            List<Tool> favesList = Tool.getFaves();
            FileWriter file = new FileWriter("faves.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < favesList.size(); i++) {
                writer.write(favesList.get(i).title);
                if (i != favesList.size() - 1) writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            // Do nothing
        }
    }

    public static void addFave(Tool fave) {
        fave.fave = true;
    }

    public static void removeFave(Tool fave) {
        fave.fave = false;
    }

    private static class Constraints extends GridBagConstraints {

        public void topPnl() {
            gridy = 1;
            fill = HORIZONTAL;
            anchor = PAGE_START;
            weightx = 1;
            weighty = 0;
        }

        public void outerPnl() {
            gridy = 2;
            fill = BOTH;
            anchor = PAGE_START;
            weightx = 1;
            weighty = 1;
        }
    }
}
