package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.event.ActionListener;

public class OptionRecipeBookUI extends OptionUI {

    JButton timeButton;
    JButton ingButton;

    public OptionRecipeBookUI(ActionListener al, RecipeBook rb) {

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
