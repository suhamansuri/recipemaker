package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MyRecipesGUI extends JFrame {
    public static final int HEIGHT = 400;
    public static final int WIDTH = 600;

    private MainMenuUI recipeBookUI;
    private RecipeBook rb;
    private LogoUI logoUI;

    public MyRecipesGUI() {
        super("RecipeBook");
        init();
        add(logoUI);
        pack();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        remove(logoUI);
        add(recipeBookUI);
        pack();
    }

    public void init() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        rb = new RecipeBook();

//        testingInitiator();

        recipeBookUI = new MainMenuUI(this.rb);
        logoUI = new LogoUI();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            for (Recipe recipe : rb.getRecipes()) {
                System.out.println(recipe.toString() + "\n");
            }
        }
    }

//    private void testingInitiator() {
//        ArrayList ingredients = new ArrayList<>();
//        ingredients.add("potatoes");
//        ingredients.add("mayonnaise");
//        ingredients.add("pickles");
//        rb.addRecipe(new Recipe("Potato salad", 30, ingredients));
//        rb.addRecipe(new Recipe("French fries", 45, new ArrayList<>()));
//        rb.addRecipe(new Recipe("French toast", 20, new ArrayList<>()));
//        rb.addRecipe(new Recipe("Coleslaw", 5, new ArrayList<>()));
//        rb.addRecipe(new Recipe("PattyMelt", 120, new ArrayList<>()));
//        rb.addRecipe(new Recipe("Milkshake", 10, new ArrayList<>()));
//        rb.addRecipe(new Recipe("Toast", 2, new ArrayList<>()));


}
