package main;

import answers.Averages;
import answers.Clocks;
import answers.boxplots.BoxPlots;
import answers.triangles.Triangles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tool {

    // Selection colours
    public static final Color SELECTED = new Color(97, 97, 97);
    public static final Color DESELECTED = new Color(238, 238, 238);

    public final String title;
    public final String intro;
    public final JPanel panel;
    private final int type;
    protected boolean fave = false;
    public final String id;

    public static final List<Tool> tools = new ArrayList<>();
    private static int nextId = 0;

    public Tool(JPanel panel, String title, String intro, int type) {
        this.panel = panel;
        this.title = title;
        this.type = type;
        this.intro = intro;
        this.id = String.valueOf(nextId);
        nextId ++;
        tools.add(this);
    }

    public static void initTools() {
        // Menu
        int type = 0;
        new Tool(new Menu(), "Welcome to " + Window.TITLE, "Solving your problems, then teaching you to solve them yourself", type);
        // Answers
        type = 1;
        new Tool(new Averages(), "Averages", "Calculate mean, median, mode and range", type);
        new Tool(new answers.Binary(), "Binary conversion", "Convert between binary and decimal", type);
        new Tool(new BoxPlots(), "Box plots", "Enter a data set and watch a box plot be drawn for it", type);
        new Tool(new Clocks(), "Clock conversion", "Convert between 12- and 24-hour clock", type);
        new Tool(new Triangles(), "Triangle calculator", "Give three values, including at least one side, and watch the rest be calculated", type);
        // Questions
        type = 2;
        new Tool(new questions.Binary(), "Binary conversion practice", "Practise converting between binary and decimal", type);
        // Favourites
        for (String fave : Window.readFaves()) {
            try {
                getTool(fave).fave = true;
            } catch (IllegalArgumentException e) {
                // Do nothing
            }
        }
        JPanel menu;
        if ((menu = getTool("Welcome to " + Window.TITLE).panel) instanceof Menu) {
            ((Menu) menu).reloadFaves();
        }
    }

    public static Tool getTool(int id) {
        return tools.get(id);
    }

    public static Tool getTool(String title) {
        for (Tool tool : tools) {
            if (tool.title.equals(title)) {
                return tool;
            }
        }
        throw new IllegalArgumentException("No tool found: " + title);
    }

    public static String[] toStringArr(List<Tool> tools) {
        List<String> returnList = new ArrayList<>();
        tools.forEach((tool) -> returnList.add(tool.title));
        return returnList.toArray(new String[0]);
    }

    public static List<Tool> getFaves() {
        List<Tool> returnList = new ArrayList<>();
        for (Tool tool : tools) {
            if (tool.fave) returnList.add(tool);
        }
        return returnList;
    }

    public static List<Tool> getAnswers() {
        List<Tool> returnList = new ArrayList<>();
        for (Tool tool : tools) {
            if (tool.type == 1) returnList.add(tool);
        }
        return returnList;
    }

    public static List<Tool> getQuestions() {
        List<Tool> returnList = new ArrayList<>();
        for (Tool tool : tools) {
            if (tool.type == 2) returnList.add(tool);
        }
        return returnList;
    }
}
