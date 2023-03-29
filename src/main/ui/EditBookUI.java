package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditBookUI extends OptionCommandUI implements ActionListener {

    Recipe thisRecipe;
    EditRecipeUI editRecipeUI;
    AddRecipeUI addRecipeUI;


    // EFFECTS: constructor
    public EditBookUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;
        initializeButtons();
        init();
    }

    // EFFECTS: initialize GUI
    @Override
    public void init() {
        backButton.addActionListener(this);
        recipeListView = generateListRecipes();
        buttonOptionPanel = generateButtons();

        recipePanel.setLayout(new BorderLayout());
        recipePanel.setMinimumSize(new Dimension(MainMenuUI.WIDTH, MainMenuUI.HEIGHT));
        header(recipePanel, "Select recipe to edit or press 'Add a recipe'");

        recipePanel.add(backButton, BorderLayout.NORTH);
        recipePanel.add(new JScrollPane(recipeListView), BorderLayout.CENTER);
        recipePanel.add(buttonOptionPanel, BorderLayout.SOUTH);
        recipePanel.setVisible(true);
    }

    // EFFECTS: initialize add button
    private JPanel generateButtons() {
        JPanel option = new JPanel();

        option.setLayout(new GridLayout(1,0));

        option.add(addButton);
        addButton.addActionListener(this);

        return option;
    }


    // EFFECTS: add all the current recipes in rb
    @Override
    JPanel generateListRecipes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 1));
        JButton listRecipe;
        List<Recipe> recipes = rb.getRecipes();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            listRecipe = new JButton(recipe.getName() + ", " + "Prep time: " + recipe.getTime() + " minutes");
            listRecipe.setActionCommand("index" + i);
            listRecipe.addActionListener(this);
            listRecipe.setPreferredSize(new Dimension(MainMenuUI.WIDTH - 40, 10));
            panel.add(listRecipe);
        }
        return panel;
    }

    // EFFECTS: returns the recipe that is selected by user
    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        int index = Integer.parseInt(actionCommand.substring(5));
        return rb.getRecipes().get(index);
    }



    // EFFECTS: responds to action done by user in GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("add")) {
            addRecipeUI = new AddRecipeUI(rb, al);
            nextPage(addRecipeUI.getPanel());
        } else if (command.contains("index")) {
            thisRecipe = getRecipeFromIndex(command);
            editRecipeUI = new EditRecipeUI(thisRecipe, rb, al);
            nextPage(editRecipeUI.getPanel());
        } else if ("back".equals(e.getActionCommand())) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }

    @Override
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
