package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MakeRecipeUI extends OptionUI implements ActionListener {

    Recipe thisRecipe;

    public MakeRecipeUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;
        init();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("index")) {
            thisRecipe = getRecipeFromIndex(command);
        } else if (command.equals("back")) {
            reset();
        }
    }

    @Override
    public void init() {
        initializeButtons("make");
        recipeListView = generateListView();

        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMinimumSize(new Dimension(RecipeBookUI.WIDTH, RecipeBookUI.HEIGHT));
        header(itemPanel, "What would you like to make?");

        itemPanel.add(backButton, BorderLayout.NORTH);
        itemPanel.add(recipeListView, BorderLayout.SOUTH);
        itemPanel.add(new JScrollPane(recipeListView), BorderLayout.CENTER);
        backButton.addActionListener(this);


    }

    @Override
    JPanel generateListView() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JButton listRecipe;
        List<Recipe> recipes = rb.getRecipes();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            listRecipe = new JButton(recipe.getName() + ","  + recipe.getTime());
            listRecipe.setActionCommand("index" + i);
            listRecipe.addActionListener(this);
            listRecipe.setPreferredSize(new Dimension(RecipeBookUI.WIDTH - 40, 25));
            panel.add(listRecipe);
        }
        panel.setVisible(true);
        return panel;
    }

    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        int index = Integer.parseInt(actionCommand.substring(5));
        return rb.getRecipes().get(index);
    }
}