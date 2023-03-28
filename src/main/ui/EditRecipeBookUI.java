package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditRecipeBookUI extends OptionUI implements ActionListener {

    Recipe thisRecipe;

    public EditRecipeBookUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;

        init();
    }

    @Override
    public void init() {
        initializeButtons("edit");
//        recipeListView = generateListView();
        buttonOptionPanel = generateButtons();

        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMinimumSize(new Dimension(RecipeBookUI.WIDTH, RecipeBookUI.HEIGHT));
        header(itemPanel, "Edit recipeBook");

        itemPanel.add(backButton, BorderLayout.NORTH);
        itemPanel.add(buttonOptionPanel, BorderLayout.SOUTH);
        itemPanel.add(new JScrollPane(recipeListView), BorderLayout.CENTER);
    }

    private JPanel generateButtons() {
        JPanel option = new JPanel();

        option.setLayout(new GridLayout(2,0));

        option.add(addButton);
        option.add(editButton);
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        backButton.addActionListener(al);

        return option;
    }


    @Override
    JPanel generateListView() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(10,1));
        JButton listRecipe;
        List<Recipe> recipes = rb.getRecipes();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            listRecipe = new JButton(recipe.getName() + ", " + recipe.getTime());
            listRecipe.setActionCommand("index" + i);
            listRecipe.addActionListener(this);
            listRecipe.setPreferredSize(new Dimension(RecipeBookUI.WIDTH - 40, 25));
            newPanel.add(listRecipe);

        }
        return newPanel;
    }

    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        int index = Integer.parseInt(actionCommand.substring(5));
        return rb.getRecipes().get(index);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("index")) {
            thisRecipe = getRecipeFromIndex(command);
            if (command == "edit") {
                itemPanel.removeAll();
                itemPanel.add(new EditRecipeUI(thisRecipe, rb, this));
                itemPanel.updateUI();
            } else if (command == "add") {
                itemPanel.removeAll();
                itemPanel.add(new AddRecipeUI(rb, this));
                itemPanel.updateUI();
            } else if (command.equals("back")) {
                reset();
            }
        }
    }
}
