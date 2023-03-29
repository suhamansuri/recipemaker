package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OptionRecipeUI extends OptionCommandUI implements ActionListener {

    JButton ingButton;
    JButton timeButton;
    String action;
    PreferenceCommandUI timePref;
    PreferenceCommandUI ingPref;
    String command;

    // EFFECTS: constructor
    public OptionRecipeUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;
        initializeButtons();
        init();
    }

    // EFFECTS; initializes gui
    @Override
    public void init() {
        backButton.addActionListener(this);
        recipeListView = generateListRecipes();
        buttonOptionPanel = generateButtons();

        recipePanel.setLayout(new BorderLayout());
        recipePanel.setMinimumSize(new Dimension(MainMenuUI.WIDTH, MainMenuUI.HEIGHT));
        header(recipePanel, "Would you like options based on time or ingredient?");

        recipePanel.add(backButton, BorderLayout.NORTH);
        recipePanel.add(recipeListView);
        recipePanel.add(buttonOptionPanel, BorderLayout.SOUTH);
        recipePanel.setVisible(true);
    }

    // EFFECTS: generates ingredient and time buttons
    private JPanel generateButtons() {
        JPanel option = new JPanel();

        option.setLayout(new GridLayout(1, 0));
        ingButton = new JButton("Ingredient");
        timeButton = new JButton("Time");
        option.add(ingButton);
        option.add(timeButton);
        ingButton.addActionListener(this);
        timeButton.addActionListener(this);
        ingButton.setActionCommand("ing");
        timeButton.setActionCommand("time");

        return option;
    }

    // EFFECTS: generates a list of recipes currently in rb
    @Override
    JPanel generateListRecipes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton listRecipe;
        List<Recipe> recipes = rb.getRecipes();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            listRecipe = new JButton(recipe.getName());
            listRecipe.setActionCommand("index" + i);
            listRecipe.addActionListener(this);
            listRecipe.setPreferredSize(new Dimension(MainMenuUI.WIDTH - 40, 25));
            panel.add(listRecipe);
        }
        return panel;
    }

    // EFFECTS: returns the recipe selected
    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        int index = Integer.parseInt(actionCommand.substring(5));
        return rb.getRecipes().get(index);
    }

    // EFFECTS: responds to action performed by user
    @Override
    public void actionPerformed(ActionEvent e) {
        command = e.getActionCommand();
        if (command.equals("ing")) {
            action = "ingredient";
            ingPref = new PreferenceCommandUI(rb, al, action);
            nextPage(ingPref.getPanel());


        } else if (command.equals("time")) {
            action = "time";
            timePref = new PreferenceCommandUI(rb, al, action);
            nextPage(timePref.getPanel());
        } else if ("back".equals(e.getActionCommand())) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }

    // EFFECTS: creates next frame
    @Override
    protected void nextPage(JPanel panel) {
        Frame newFrame = new JFrame();
        newFrame.add(panel);
        newFrame.toFront();
        newFrame.setMinimumSize(new Dimension(300, 80));
        newFrame.setResizable(true);
        newFrame.setVisible(true);
        updateUI();
    }
}
