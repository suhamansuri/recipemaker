package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class OptionCommandUI extends JPanel implements ActionListener {
    RecipeBook rb;
    ActionListener al;
    JPanel recipePanel = new JPanel();
    JPanel recipeListView;
    JPanel buttonOptionPanel;
    JButton backButton;
    JButton addButton;
    JButton editButton;

    public static final int HF = 400;
    public static final int WF = 600;


    // EFFECTS: initializes buttons that are used
    void initializeButtons() {
        backButton = new JButton("Back");
        backButton.setActionCommand("back");

        addButton = new JButton("Add a recipe");
        addButton.setActionCommand("add");
        editButton = new JButton("Edit a recipe");
        editButton.setActionCommand("edit");
    }

    // EFFECTS: initializes
    public abstract void init();

    // EFFECTS: generates a list of recipes currently in rb
    abstract JPanel generateListRecipes();

    // EFFECTS; returns a recipe that was selected
    abstract Recipe getRecipeFromIndex(String actionCommand);

    // EFFECTS: returns the main Panel for class
    protected JPanel getPanel() {
        return this.recipePanel;
    }


    // REQUIRES: txt must not be empty
    // EFFECTS: Creates header for panel
    protected void header(JPanel panel, String txt) {
        panel.setBorder(BorderFactory.createTitledBorder(txt));
    }

    // EFFECTS: creates the next frame
    protected void nextPage(JPanel panel) {
        Frame newFrame = new JFrame();
        newFrame.add(panel);
        newFrame.toFront();
        newFrame.setMinimumSize(new Dimension(WF,HF));
        newFrame.setResizable(true);
        newFrame.setVisible(true);
        updateUI();
    }

}
