package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MakeRecipeUI extends OptionCommandUI implements ActionListener {

    Recipe thisRecipe;

    public MakeRecipeUI(ActionListener al, RecipeBook rb) {
        this.al = al;
        this.rb = rb;
        initializeButtons();
        init();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.contains("index")) {
            thisRecipe = getRecipeFromIndex(command);
            JFrame frame = new JFrame("Ingredients for " + thisRecipe.getName());
            frame.setMinimumSize(new Dimension(300,200));
            String[] data = thisRecipe.getIngredients().toArray(new String[0]);
            frame.add(new JList(data));
            frame.pack();
            frame.setVisible(true);
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
        header(recipePanel, "What would you like to make?");

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

            listRecipe = new JButton(recipe.getName() + ", "  + "Prep time: " + recipe.getTime() + " minutes");
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