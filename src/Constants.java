import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    // General
    public static final String TITLE = "Maths";
    public static final int[] WN_SIZE = {700, 500};

    // Identifiers
    public static final String[] answer_options = {
            "Averages",
            "Binary conversion",
            "Clock conversion",
            "Triangle calculator",
            "Box plots"
    };
    public static final String[] learn_options = {

    };
    public static String[] faves = Main.read_faves();

    public static String[] IDENTIFIERS = update_ids();

    public static String[] update_ids() {
        String[] temp_ids = new String[answer_options.length + faves.length + learn_options.length + 1];
        temp_ids[0] = "Welcome to " + TITLE;
        System.arraycopy(answer_options, 0, temp_ids, 1, answer_options.length);
        System.arraycopy(faves, 0, temp_ids, answer_options.length, faves.length);
        System.arraycopy(learn_options, 0, temp_ids, answer_options.length + faves.length, learn_options.length);
        return temp_ids;
    }

    public static Map<String, String> update_intros() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < IDENTIFIERS.length; i++) {
            map.put(IDENTIFIERS[i], INTRO_TEXT[i]);
        }
        return map;
    }

    // Selection colours
    public static final Color SELECTED = new Color(97, 97, 97);
    public static final Color DESELECTED = new Color(238, 238, 238);

    // Intro texts
    public static String[] INTRO_TEXT = {
            "Solving your problems, then teaching you to solve them yourself",
            "Calculate mean, median, mode and range",
            "Convert between binary and decimal",
            "Convert between 12- and 24-hour clock",
            "Give three values, including at least one side, and watch the rest be calculated",
            "Enter a data set and watch a box plot be drawn for it"
    };

    public static Map<String, String> INTRO_TEXT_MAP = update_intros();
}
