package main;

import main.Window;
import main.Tool;
import static main.Window.window;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class Menu extends JPanel implements ActionListener, ListSelectionListener {

    private final boolean[] isClearing = {false, false, false}; // For list box selection events
    private final JButton goBtn = new JButton("Go"); // Go button

    private final JList<String> answersList = new JList<>(); // Answers list box
    private final DefaultListModel<String> favesModel = new DefaultListModel<>(); // Favourites list model
    private final JList<String> favesList = new JList<>(favesModel); // Favourites list box
    private final JList<String> questionsList = new JList<>(); // Learning list box

    // Icons
    private static final ImageIcon ADD_ICON = new ImageIcon("add_icon.png");
    private static final ImageIcon REMOVE_ICON = new ImageIcon("remove_icon.png");

    // Favourites buttons
    private final JButton addFavesBtn = new JButton(ADD_ICON);
    private final JButton removeFavesBtn = new JButton(REMOVE_ICON);
    
    public Menu() {
        super();
        reloadFaves();
        // Set layout
        setLayout(new GridBagLayout());
        MenuCns c = new MenuCns();
        // Answers label
        c.answersLbl();
        JLabel answersLbl = new JLabel("Calculators:");
        Font font = answersLbl.getFont();
        Map<TextAttribute, Object> fontAttributes = new HashMap<>(font.getAttributes());
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        answersLbl.setFont(font.deriveFont(fontAttributes));
        add(answersLbl, c);
        // Favourites label
        c.favesLbl();
        JLabel favesLbl = new JLabel("Favourites:");
        favesLbl.setFont(font.deriveFont(fontAttributes));
        add(favesLbl, c);
        // Add to favourites button
        c.addFavesBtn();
        addFavesBtn.addActionListener(this);
        add(addFavesBtn, c);
        // Remove from favourites button
        c.removeFavesBtn();
        removeFavesBtn.addActionListener(this);
        add(removeFavesBtn, c);
        // Learning label
        c.questionsLbl();
        JLabel questionsLbl = new JLabel("Questions:");
        questionsLbl.setFont(font.deriveFont(fontAttributes));
        add(questionsLbl, c);
        // Answers list box
        c.answersList();
        answersList.addListSelectionListener(this);
        add(answersList, c);
        // Favourites list box
        c.favesList();
        favesList.addListSelectionListener(this);
        add(favesList, c);
        // Learning list box
        c.questionsList();
        questionsList.addListSelectionListener(this);
        add(questionsList, c);
        // Go button
        c.goBtn();
        goBtn.addActionListener(this);
        add(goBtn, c);
        // Reload tools
        reloadTools();
    }

    public void reloadFaves() {
        favesModel.clear();
        for (Tool fave : Tool.getFaves()) {
            favesModel.addElement(fave.title);
        }
    }

    public void reloadTools() {
        answersList.setListData(Tool.toStringArr(Tool.getAnswers()));
        questionsList.setListData(Tool.toStringArr(Tool.getQuestions()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == goBtn) {
                if (answersList.getSelectedIndex() != -1) {
                    window.switchPanel(Tool.getTool(answersList.getSelectedValue()));
                } else if (favesList.getSelectedIndex() != -1) {
                    window.switchPanel(Tool.getTool(favesList.getSelectedValue()));
                } else if (questionsList.getSelectedIndex() != -1) {
                    window.switchPanel(Tool.getTool(questionsList.getSelectedValue()));
                }
            } else if (e.getSource() == addFavesBtn) {
                if (answersList.getSelectedIndex() != -1) {
                    Window.addFave(Tool.getTool(answersList.getSelectedValue()));
                    Window.writeFaves();
                    favesModel.addElement(answersList.getSelectedValue());
                } else if (questionsList.getSelectedIndex() != -1) {
                    Window.addFave(Tool.getTool(questionsList.getSelectedValue()));
                    Window.writeFaves();
                    favesModel.addElement(questionsList.getSelectedValue());
                }
            } else if (e.getSource() == removeFavesBtn && favesList.getSelectedIndex() != -1) {
                Window.removeFave(Tool.getTool(favesList.getSelectedValue()));
                favesModel.removeElementAt(favesList.getSelectedIndex());
                Window.writeFaves();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == answersList) {
            if (!isClearing[1] && !isClearing[2]) {
                isClearing[0] = true;
                questionsList.clearSelection();
                favesList.clearSelection();
                isClearing[0] = false;
            }
        } else if (e.getSource() == favesList) {
            if (!isClearing[0] && !isClearing[2]) {
                isClearing[1] = true;
                answersList.clearSelection();
                questionsList.clearSelection();
                isClearing[1] = false;
            }
        } else if (e.getSource() == questionsList) {
            if (!isClearing[0] && !isClearing[1]) {
                isClearing[2] = true;
                answersList.clearSelection();
                favesList.clearSelection();
                isClearing[2] = false;
            }
        }
    }

    private static class MenuCns extends GridBagConstraints {

        public void answersLbl() {
            gridx = 1;
            gridy = 1;
            gridwidth = 1;
            fill = HORIZONTAL;
            weightx = 0.5;
            weighty = 0;
            anchor = PAGE_END;
            insets = new Insets(10, 5, 4, 4);
        }

        public void favesLbl() {
            gridx = 2;
            gridy = 1;
            gridwidth = 1;
            fill = HORIZONTAL;
            weightx = 0.5;
            weighty = 0;
            anchor = PAGE_END;
            insets = new Insets(10, 4, 4, 10);
        }

        public void addFavesBtn() {
            gridx = 3;
            gridy = 1;
            gridwidth = 1;
            fill = NONE;
            weightx = 0;
            weighty = 0;
            anchor = LINE_END;
            insets = new Insets(10, 0, 0, 0);
        }

        public void removeFavesBtn() {
            gridx = 4;
            gridy = 1;
            gridwidth = 1;
            fill = NONE;
            weightx = 0;
            weighty = 0;
            anchor = LINE_END;
            insets = new Insets(10, 0, 0, 4);
        }

        public void questionsLbl() {
            gridx = 5;
            gridy = 1;
            gridwidth = 1;
            fill = HORIZONTAL;
            weightx = 0.5;
            weighty = 0;
            anchor = PAGE_END;
            insets = new Insets(10, 4, 4, 5);
        }

        public void answersList() {
            gridx = 1;
            gridy = 2;
            gridwidth = 1;
            fill = BOTH;
            weightx = 1;
            weighty = 1;
            anchor = LINE_START;
            insets = new Insets(4, 5, 5, 4);
        }

        public void favesList() {
            gridx = 2;
            gridy = 2;
            gridwidth = 3;
            fill = BOTH;
            weightx = 1;
            weighty = 1;
            anchor = CENTER;
            insets = new Insets(4, 4, 5, 4);
        }

        public void questionsList() {
            gridx = 5;
            gridy = 2;
            gridwidth = 1;
            fill = BOTH;
            weightx = 1;
            weighty = 1;
            anchor = LINE_END;
            insets = new Insets(4, 4, 5, 5);
        }

        public void goBtn() {
            gridx = 1;
            gridy = 3;
            gridwidth = 5;
            fill = BOTH;
            weightx = 0.5;
            weighty = 0.2;
            anchor = PAGE_END;
            insets = new Insets(5, 5, 5, 5);
        }
    }
}
