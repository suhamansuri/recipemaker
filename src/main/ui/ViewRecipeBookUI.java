package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewRecipeBookUI extends OptionCommandUI {

    public ViewRecipeBookUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;
        initializeButtons();
        init();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("index")) {
            //
        } else if ("back".equals(e.getActionCommand())) {
            JComponent comp = (JComponent)e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }

    @Override
    public void init() {
        backButton.addActionListener(this);
        recipeListView = generateListView();

        recipePanel.setLayout(new BorderLayout());
        recipePanel.setMinimumSize(new Dimension(MainMenuUI.WIDTH, MainMenuUI.HEIGHT));
        header(recipePanel, "Here are all your recipes!");

        recipePanel.add(backButton, BorderLayout.NORTH);
        recipePanel.add(recipeListView, BorderLayout.SOUTH);
        recipePanel.add(new JScrollPane(recipeListView), BorderLayout.CENTER);
        recipePanel.setVisible(true);
    }

    @Override
    JPanel generateListView() {
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

    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        int index = Integer.parseInt(actionCommand.substring(5));
        return rb.getRecipes().get(index);
    }
}