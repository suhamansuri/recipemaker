package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewRecipeBookUI extends OptionUI {

    public ViewRecipeBookUI(ActionListener al, RecipeBook rb) {
        super();

    }

    @Override
    public void init() {
    }


    @Override
    JPanel generateListView() {
        return null;
    }

    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        return null;
    }
}
