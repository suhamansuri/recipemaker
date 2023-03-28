package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class OptionUI {
    RecipeBook rb;
    ActionListener al;
    String activeOption = "";
    JPanel itemPanel = new JPanel();
    JPanel recipeListView;
    JPanel buttonOptionPanel;
    JButton backButton;
    JButton addButton;
    JButton editButton;


    void initializeButtons(String text) {
        backButton = new JButton("Back");
        backButton.setActionCommand("back");

        addButton = new JButton("Add a recipe");
        addButton.setActionCommand("back");
        editButton = new JButton("Edit a recipe");
        editButton.setActionCommand("edit");
    }

    public abstract void init();


    abstract JPanel generateListView();

    abstract Recipe getRecipeFromIndex(String actionCommand);

    // EFFECTS: returns the main Panel for class
    protected JPanel getPanel() {
        return this.itemPanel;
    }

    public void reset() {
        refresh();
        itemPanel.removeAll();
        itemPanel.add(backButton, BorderLayout.NORTH);
        itemPanel.add(recipeListView, BorderLayout.CENTER);
        itemPanel.updateUI();
    }

    protected void refresh() {
        recipeListView = generateListView();
        itemPanel.updateUI();
    }

    // REQUIRES: txt must not be empty
    // EFFECTS: Creates header for panel
    protected void header(JPanel panel, String txt) {
        panel.setBorder(BorderFactory.createTitledBorder(txt));
    }


}
